package bsuir.model.stationDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "station", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    @Column(name = "id_station", nullable = false)
    @EqualsAndHashCode.Include
    private long idStation;

    @Basic
    @Column(name = "station", nullable = false, length = 45)
    private String station;

    @Basic
    @Column(name = "city", nullable = false)
    private long city;
}
