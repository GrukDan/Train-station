package bsuir.model.taskDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "alternative", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alternative {

    @Id
    @Column(name = "id_alternative", nullable = false)
    @EqualsAndHashCode.Include
    private long idAlternative;

    @Basic
    @Column(name = "trip", nullable = false)
    private long trip;

    @Basic
    @Column(name = "task", nullable = false)
    private long task;
}
