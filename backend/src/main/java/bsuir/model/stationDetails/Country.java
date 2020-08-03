package bsuir.model.stationDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "country", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @Column(name = "id_country", nullable = false)
    @EqualsAndHashCode.Include
    private long idCountry;

    @Basic
    @Column(name = "country", nullable = false, length = 45)
    private String country;
}
