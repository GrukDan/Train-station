package bsuir.model.stationDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "trip", schema = "train_station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @Column(name = "id_trip", nullable = false)
    private long idTrip;

    @Basic
    @Column(name = "departure_station", nullable = false)
    private long departureStation;

    @Basic
    @Column(name = "arrival_station", nullable = false)
    private long arrivalStation;

    @Basic
    @Column(name = "train", nullable = false)
    private long train;

    public Trip(Trip trip){
        idTrip = trip.idTrip;
        departureStation = trip.departureStation;
        arrivalStation = trip.arrivalStation;
        train = trip.train;
    }
}
