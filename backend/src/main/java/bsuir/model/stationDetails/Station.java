package bsuir.model.stationDetails;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Station {
    private long idStation;
    private String station;
    private long city;

    @Id
    @Column(name = "id_station", nullable = false)
    public long getIdStation() {
        return idStation;
    }

    public void setIdStation(long idStation) {
        this.idStation = idStation;
    }

    @Basic
    @Column(name = "station", nullable = false, length = 45)
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Basic
    @Column(name = "city", nullable = false)
    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station1 = (Station) o;
        return idStation == station1.idStation &&
                city == station1.city &&
                Objects.equals(station, station1.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStation, station, city);
    }

}
