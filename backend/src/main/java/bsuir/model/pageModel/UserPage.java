package bsuir.model.pageModel;

import bsuir.model.userDetails.User;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserPage extends Page {

    private List<User> users;

    public UserPage(int page, int size, long totalPages, boolean direction, String parameter, List<User> users) {
        super(page, size, totalPages, direction, parameter);
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserPage userPage = (UserPage) o;
        return Objects.equals(users, userPage.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), users);
    }
}
