package bsuir.service.userDetails.Impl;

import bsuir.model.userDetails.User;
import bsuir.repository.userDetails.RoleRepository;
import bsuir.repository.userDetails.UserRepository;
import bsuir.service.userDetails.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private String[] parameters = {""};

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) throws NullPointerException{
        if(user!=null){
            Optional<User> userOptional = userRepository.findById(user.getIdUser());
            if(!userOptional.isPresent())
                throw new NullPointerException("Data for " + user.getName() + " not found");
            user.setLogin(userOptional.get().getLogin());
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
    public List<User> getPageSorted(int page, int size, String parameter, boolean direction, String search) {
        return null;
    }

}
