package bsuir.model.pageModel;

import bsuir.model.userDetails.User;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserPage extends Page {

    private List<User> users;

    @Builder
    public UserPage(int page, int size, long totalElements, boolean direction, String parameter, List<User> users) {
        super(page, size, totalElements, direction, parameter);
        this.users = users;
    }
}
