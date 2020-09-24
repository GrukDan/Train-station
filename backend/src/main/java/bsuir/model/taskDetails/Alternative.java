package bsuir.model.taskDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "alternative", schema = "train_station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alternative {

    @Id
    @Column(name = "id_alternative", nullable = false)
    private long idAlternative;

    @Basic
    @Column(name = "trip", nullable = false)
    private long trip;

    @Basic
    @Column(name = "task", nullable = false)
    private long task;
}
