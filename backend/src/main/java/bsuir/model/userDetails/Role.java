package bsuir.model.userDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role", schema = "train_station")
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id_role", nullable = false)
    private long idRole;

    @Basic
    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role1 = (Role) o;
        return idRole == role1.idRole &&
                role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, role);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("idRole=").append(idRole);
        sb.append(", role='").append(role).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
