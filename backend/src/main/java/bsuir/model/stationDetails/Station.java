package bsuir.model.stationDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "station", schema = "train_station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    @Column(name = "id_station", nullable = false)
    private long idStation;

    @Basic
    @Column(name = "station", nullable = false, length = 45)
    @NotBlank
    private String station;

    @Basic
    @Column(name = "city", nullable = false)
    private long city;
}
