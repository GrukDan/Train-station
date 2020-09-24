package bsuir.service.userDetails.Impl;

import bsuir.model.pageModel.UserPage;
import bsuir.model.userDetails.Role;
import bsuir.model.userDetails.User;
import bsuir.sequenceGenerator.PassayGenerator;
import bsuir.repository.userDetails.UserRepository;
import bsuir.service.userDetails.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PassayGenerator passayGenerator;
//    private final BCryptPasswordEncoder passwordEncoder;
    private final String[] parameters;

    {
        parameters = new String[]{"name", "surname", "email"};
    }

    public UserServiceImpl(UserRepository userRepository, PassayGenerator passayGenerator) {
        this.userRepository = userRepository;
        this.passayGenerator = passayGenerator;

    }

    @Override
    public User save(User user) {
        String password = passayGenerator.generatePassword();
//        user.setPassword(passwordEncoder.encode(password));
        user.setPassword(password);
        Set<Role> role = user.getRoles();
        user.setRoles(null);
        userRepository.save(user);
        user = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        user.setRoles(role);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(Long id) throws ChangeSetPersister.NotFoundException {
        return userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public User update(User user) throws NullPointerException {
        User savedUser = userRepository.findById(user.getIdUser()).orElseThrow(NullPointerException::new);
        user.setPassword(savedUser.getPassword());
        user.setRoles(savedUser.getRoles());
        return userRepository.save(savedUser.update(user));
    }

    @Override
    public List<String> getSortParameters() {
        return Arrays.asList(parameters);
    }

    @Override
    public UserPage getPage(int page, int size, boolean direction, String parameter) {
        Page<User> users = getPageSorted(page, size, direction, parameter);
        return new UserPage(page, size, users.getTotalElements(), direction, parameter, users.getContent());
    }

    private Page<User> getPageSorted(int page, int size, boolean direction, String parameter) {
        return direction
                ? userRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, parameter)))
                : userRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, parameter)));
    }

    @Override
    public List<User> getUsersById(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public List<User> getExperts() {
        return userRepository.findAllByRoleName("EXPERT");
    }

    @Override
    public List<User> getAllByTaskId(Long idTask) {
        List<User> users = userRepository.findAllByTaskId(idTask);
        users.forEach(User::forgetPassword);
        return users;
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public User findByEmail(String email) throws ChangeSetPersister.NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public User findByEmailAndPassword(String username, String password) {
        return userRepository.findByEmailAndPassword(username,password);
    }
}
