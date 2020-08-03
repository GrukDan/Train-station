package bsuir.model.userDetails;

import bsuir.model.taskDetails.Task;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;


@Entity
@Table(name = "user", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id_user", nullable = false)
    @EqualsAndHashCode.Include
    private long idUser;

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Email(message = "Email should be valid")
    @EqualsAndHashCode.Include
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    @EqualsAndHashCode.Include
    @JsonProperty()
    private String password;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "user_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks;

    public User(@NonNull User user){
            this.idUser = user.idUser;
            this.name = user.name;
            this.surname = user.surname;
            this.email = user.email;
            this.password = user.password;
    }

    public void forgetPassword(){
        password = null;
    }
}
