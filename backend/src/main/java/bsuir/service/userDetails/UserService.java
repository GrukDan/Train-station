package bsuir.service.userDetails;

import bsuir.model.pageModel.UserPage;
import bsuir.model.userDetails.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void delete(long id);

    User getById(long id);

    User update(User user);

    List<String> getSortParameters();

    UserPage getPage(int page, int size, boolean direction, String parameter);
}
