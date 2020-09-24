package bsuir.model.stationDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "city", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @Column(name = "id_city", nullable = false)
    private long idCity;

    @Basic
    @Column(name = "city", nullable = false, length = 45)
    @NotBlank
    private String city;

    @Basic
    @Column(name = "country", nullable = false)
    private long country;
}
