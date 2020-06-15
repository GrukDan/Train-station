package bsuir.model.userDetails;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role {
    private long idRole;
    private String role;
    private Set<User> users;

    @Id
    @Column(name = "id_role", nullable = false)
    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 20)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return idRole == role1.idRole &&
                Objects.equals(role, role1.role);
    }


    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, role);
    }
}
