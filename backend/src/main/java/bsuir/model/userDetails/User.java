package bsuir.model.userDetails;

import bsuir.model.taskDetails.Task;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "user", schema = "train_station")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id_user", nullable = false)
    private long idUser;

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    @NotBlank
    private String name;

    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    @NotBlank
    private String surname;

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    @Email(message = "Email should be valid")
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    @JsonProperty
    @NotBlank
    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id_role"))
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name = "user_tasks",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "task_id",referencedColumnName = "id_task"))
    private Set<Task> tasks;

    public User update(@NonNull User user){
        this.idUser = user.idUser;
        this.name = user.name;
        this.surname = user.surname;
        this.email = user.email;
        this.password = user.password;
        this.roles = user.roles;
        this.tasks = user.tasks;
        return this;
    }

    public void forgetPassword() {
        password = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return idUser == user.idUser &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                email.equals(user.email) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, name, surname, email, password);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", idUser=").append(idUser);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
