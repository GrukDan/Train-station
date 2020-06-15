package bsuir.model.stationDetails;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Trip {
    private long idTrip;
    private long station;
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
    @Column(name = "station", nullable = false)
    public long getStation() {
        return station;
    }

    public void setStation(long station) {
        this.station = station;
    }

    @Basic
    @Column(name = "train", nullable = false)
    public long getTrain() {
        return train;
    }

    public void setTrain(long train) {
        this.train = train;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return idTrip == trip.idTrip &&
                station == trip.station &&
                train == trip.train;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrip, station, train);
    }
}
