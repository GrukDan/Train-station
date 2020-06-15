package bsuir.service.TripDetails;

import bsuir.model.stationDetails.Trip;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TripService {
    Trip save(Trip trip);

    void delete(long id);

    Trip getById(long id);

    List<Trip> getAll();

    List<Trip> getAllByStation(long station);

    List<Trip> getAllByTrain(long train);

    List<Trip> getPageSorted(int page, int size, String parameter, boolean direction);

    List<Trip> getPageByTrainOrStationSorted(int page, int size, String parameter, boolean direction, String search);
}
