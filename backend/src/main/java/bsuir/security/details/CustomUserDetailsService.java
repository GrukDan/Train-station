package bsuir.security.details;


import bsuir.model.userDetails.User;
import bsuir.service.userDetails.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.findByEmail(username);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
        return CustomUserDetails.of(user);
    }
}
