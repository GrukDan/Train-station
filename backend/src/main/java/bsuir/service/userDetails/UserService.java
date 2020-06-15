package bsuir.service.userDetails;

import bsuir.model.userDetails.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void delete(long id);

    User getById(long id);

    User update(User user);

    List<String> getSortParameters();

    List<User> getPageSorted(int page, int size, String parameter, boolean direction, String search);
}
