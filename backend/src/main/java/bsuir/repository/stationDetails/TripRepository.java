package bsuir.repository.stationDetails;

import bsuir.model.stationDetails.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findAllByStation(long station);

    List<Trip> findAllByTrain(long train);

    Page<Trip> findAllByTrainOrStation(String search, PageRequest of);
}
