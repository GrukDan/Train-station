package bsuir.service.userDetails;

import bsuir.model.pageModel.UserPage;
import bsuir.model.userDetails.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    void delete(Long id);

    User getById(Long id) throws ChangeSetPersister.NotFoundException;

    User findByEmail(String email) throws ChangeSetPersister.NotFoundException;

    User update(User user);

    List<String> getSortParameters();

    UserPage getPage(int page, int size, boolean direction, String parameter);

    List<User> getUsersById(List<Long> ids);

    List<User> getExperts();

    List<User> getAllByTaskId(Long idTask);

    List<User> saveAll(List<User> users);

    User findByEmailAndPassword(String username, String password);
}
