package bsuir.model.stationDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "city", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @Column(name = "id_city", nullable = false)
    @EqualsAndHashCode.Include
    private long idCity;

    @Basic
    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @Basic
    @Column(name = "country", nullable = false)
    private long country;
}
