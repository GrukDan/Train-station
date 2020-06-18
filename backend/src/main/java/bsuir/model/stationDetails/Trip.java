package bsuir.model.stationDetails;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Trip {
    private long idTrip;
    private long departureStation;
    private long arrivalStation;
    private long train;

    @Id
    @Column(name = "id_trip", nullable = false)
    public long getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(long idTrip) {
        this.idTrip = idTrip;
    }

    @Basic
    @Column(name = "train", nullable = false)
    public long getTrain() {
        return train;
    }

    public void setTrain(long train) {
        this.train = train;
    }

    @Basic
    @Column(name = "departure_station", nullable = false)
    public long getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(long departureStation) {
        this.departureStation = departureStation;
    }

    @Basic
    @Column(name = "arrival_station", nullable = false)
    public long getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(long arrivalStation) {
        this.arrivalStation = arrivalStation;
    }
}
