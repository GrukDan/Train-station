package bsuir.service.userDetails;

import bsuir.model.pageModel.UserPage;
import bsuir.model.userDetails.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserService {
    User save(User user);

    void delete(long id);

    User getById(long id) throws ChangeSetPersister.NotFoundException;

    User update(User user);

    List<String> getSortParameters();

    UserPage getPage(int page, int size, boolean direction, String parameter);

    List<User> getUsersById(List<Long> ids);

    List<User> getExperts();
}
