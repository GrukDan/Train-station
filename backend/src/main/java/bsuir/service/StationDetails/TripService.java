package bsuir.service.StationDetails;

import bsuir.model.pageModel.TripPage;
import bsuir.model.stationDetails.Trip;

import java.util.List;


public interface TripService {
    Trip save(Trip trip);

    void delete(long id);

    Trip getById(long id);

    List<Trip> getAll();

    List<Trip> getAllByDepartureStation(long departureStation);

    List<Trip> getAllByArrivalStation(long arrivalStation);

    List<Trip> getAllByDepartureStationAndArrivalStation(long departureStation, long arrivalStation);

    List<Trip> getAllByTrain(long train);

    TripPage getPageSorted(int page, int size, boolean direction, String parameter);

}
