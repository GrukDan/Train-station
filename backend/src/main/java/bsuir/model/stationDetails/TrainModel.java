package bsuir.model.stationDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "train_model", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainModel {

    @Id
    @Column(name = "id_train_model", nullable = false)
    @EqualsAndHashCode.Include
    private long idTrainModel;

    @Basic
    @Column(name = "model", nullable = false, length = 45)
    @NotBlank
    private String model;
}
