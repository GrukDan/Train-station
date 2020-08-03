package bsuir.model.viewModel;

import bsuir.model.userDetails.Role;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModel {

    private String name;

    private String surname;

    private String email;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Role> roles;
}
