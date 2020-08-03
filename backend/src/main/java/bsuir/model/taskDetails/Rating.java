package bsuir.model.taskDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rating", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @Column(name = "id_rating", nullable = false)
    @EqualsAndHashCode.Include
    private long idRating;

    @Basic
    @Column(name = "alternative1", nullable = false)
    private long alternative1;

    @Basic
    @Column(name = "alternative2", nullable = false)
    private long alternative2;

    @Basic
    @Column(name = "expert", nullable = false)
    private long expert;

    @Basic
    @Column(name = "rating", nullable = false)
    private int rating;

}
