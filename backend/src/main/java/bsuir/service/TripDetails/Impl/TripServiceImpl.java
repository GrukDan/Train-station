package bsuir.service.TripDetails.Impl;

import bsuir.model.stationDetails.Trip;
import bsuir.repository.stationDetails.TripRepository;
import bsuir.service.TripDetails.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Trip> getAllByStation(long station) {
        return tripRepository.findAllByStation(station);
    }

    @Override
    public List<Trip> getAllByTrain(long train) {
        return tripRepository.findAllByTrain(train);
    }

    @Override
    public List<Trip> getPageSorted(int page, int size, String parameter, boolean direction) {
        return direction ? tripRepository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,parameter))).getContent()
        : tripRepository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,parameter))).getContent();
    }

    @Override
    public List<Trip> getPageByTrainOrStationSorted(int page, int size, String parameter, boolean direction, String search) {
        return direction ? tripRepository.findAllByTrainOrStation(search,PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,parameter))).getContent()
                : tripRepository.findAllByTrainOrStation(search,PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,parameter))).getContent();
    }
}
