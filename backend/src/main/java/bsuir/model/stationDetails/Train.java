package bsuir.model.stationDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "train", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @Column(name = "id_train", nullable = false)
    @EqualsAndHashCode.Include
    private long idTrain;

    @Basic
    @Column(name = "model", nullable = false)
    private long model;

    @Basic
    @Column(name = "date_of_creation", nullable = false)
    private Date dateOfCreation;
}
