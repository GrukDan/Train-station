package bsuir.model.viewModel;

import bsuir.model.userDetails.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;


@Data
@NoArgsConstructor
public class UserViewModel {

    private String name;

    private String surname;

    private String email;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Role> roles;
}
