package bsuir.repository.stationDetails;

import bsuir.model.stationDetails.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {

    List<Trip> findAllByTrain(long train);

    List<Trip> findAllByArrivalStation(long arrivalStation);

    List<Trip> findAllByDepartureStation(long departureStation);

    List<Trip> findAllByDepartureStationAndArrivalStation(long departureStation, long arrivalStation);
}
