package bsuir.model.pageModel;

import bsuir.model.userDetails.User;

import java.util.List;

public class UserPage extends Page {
    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserPage(int page, int size, long totalPages, boolean direction, String parameter, List<User> users) {
        super(page, size, totalPages, direction, parameter);
        this.users = users;
    }

    public UserPage(List<User> users) {
        this.users = users;
    }
}
