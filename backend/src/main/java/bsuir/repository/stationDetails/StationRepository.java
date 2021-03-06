package bsuir.repository.stationDetails;

import bsuir.model.stationDetails.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station,Long> {
    List<Station> findAllByCity(long city);
}
