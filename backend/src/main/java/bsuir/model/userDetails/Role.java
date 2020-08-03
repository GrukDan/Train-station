package bsuir.model.userDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "id_role", nullable = false)
    @EqualsAndHashCode.Include
    private long idRole;

    @Basic
    @Column(name = "role", nullable = false, length = 20)
    @EqualsAndHashCode.Include
    private String role;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
