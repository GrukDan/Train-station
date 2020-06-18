package bsuir.service.StationDetails.Impl;

import bsuir.model.pageModel.TripPage;
import bsuir.model.stationDetails.Trip;
import bsuir.repository.stationDetails.TripRepository;
import bsuir.service.StationDetails.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Trip save(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public void delete(long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public Trip getById(long id) {
        return tripRepository.findById(id).get();
    }

    @Override
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    @Override
    public List<Trip> getAllByDepartureStation(long departureStation) {
        return tripRepository.findAllByDepartureStation(departureStation);
    }

    @Override
    public List<Trip> getAllByArrivalStation(long arrivalStation) {
        return tripRepository.findAllByArrivalStation(arrivalStation);
    }

    @Override
    public List<Trip> getAllByDepartureStationAndArrivalStation(long departureStation,long arrivalStation) {
        return tripRepository.findAllByDepartureStationAndArrivalStation(departureStation,arrivalStation);
    }

    @Override
    public List<Trip> getAllByTrain(long train) {
        return tripRepository.findAllByTrain(train);
    }

    @Override
    public TripPage getPageSorted(int page, int size, boolean direction, String parameter) {
        return null;
    }
}
