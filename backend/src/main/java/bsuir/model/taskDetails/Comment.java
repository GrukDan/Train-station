package bsuir.model.taskDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "comment", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @Column(name = "id_comment", nullable = false)
    @EqualsAndHashCode.Include
    private long idComment;

    @Basic
    @Column(name = "comment", nullable = false, length = -1)
    @NotBlank
    private String comment;

    @Basic
    @Column(name = "place", nullable = false)
    private long place;

    @Basic
    @Column(name = "time_of_creation", nullable = false)
    private Timestamp timeOfCreation;

    @Basic
    @Column(name = "owner", nullable = false)
    private long owner;
}
