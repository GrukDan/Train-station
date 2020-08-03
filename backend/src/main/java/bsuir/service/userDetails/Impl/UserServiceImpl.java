package bsuir.service.userDetails.Impl;

import bsuir.model.pageModel.UserPage;
import bsuir.model.userDetails.Role;
import bsuir.model.userDetails.User;
import bsuir.passayGenerator.PassayGenerator;
import bsuir.repository.userDetails.RoleRepository;
import bsuir.repository.userDetails.UserRepository;
import bsuir.service.userDetails.RoleService;
import bsuir.service.userDetails.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private String[] parameters;

    {
        parameters = new String[]{"name", "surname", "email"};
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PassayGenerator passayGenerator;

    @Override
    public User save(User user) {
        user.setPassword(passayGenerator.generatePassword());
        Set<Role> role = user.getRoles();
        user.setRoles(null);
        userRepository.save(user);
        user = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        user.setRoles(role);

        log.info("Save new user: " + user);

        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(long id) throws ChangeSetPersister.NotFoundException {
        return userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public User update(User user) throws NullPointerException {
        if (user != null) {
            Optional<User> userOptional = userRepository.findById(user.getIdUser());
            if (!userOptional.isPresent())
                throw new NullPointerException("Data for " + user.getName() + " not found");
            user.setPassword(userOptional.get().getPassword());
        }
        //todo:
        user = userRepository.save(user);
        return null;
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
}
