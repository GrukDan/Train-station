package bsuir.service.TripDetails.Impl;

import bsuir.model.stationDetails.Station;
import bsuir.repository.stationDetails.StationRepository;
import bsuir.service.TripDetails.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Station save(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public Station getById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {
        stationRepository.deleteById(id);
    }

    @Override
    public List<Station> getAllByCity(long city) {
        return stationRepository.findAllByCity(city);
    }
}
