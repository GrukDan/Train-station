package bsuir.service.StationDetails;

import bsuir.exception.AlreadyExists;
import bsuir.model.pageModel.TripPage;
import bsuir.model.stationDetails.Trip;
import bsuir.model.viewModel.TripRecord;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


public interface TripService {
    Trip save(Trip trip) throws AlreadyExists;

    void delete(Long id);

    Trip getById(Long id) throws ChangeSetPersister.NotFoundException;

    List<Trip> getAll();

    List<Trip> getAllByDepartureStation(Long departureStation);

    List<Trip> getAllByArrivalStation(Long arrivalStation);

    List<Trip> getAllByDepartureStationAndArrivalStation(Long departureStation, Long arrivalStation);

    List<Trip> getAllByTrain(Long train);

    TripPage getPage(int page, int size, boolean direction, String parameter);

    List<TripRecord> getAllTripRecords();
}
