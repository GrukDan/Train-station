package bsuir.model.stationDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "country", schema = "train_station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @Column(name = "id_country", nullable = false)
    private long idCountry;

    @Basic
    @Column(name = "country", nullable = false, length = 45)
    @NotBlank
    private String country;
}
