package bsuir.service.StationDetails.Impl;

import bsuir.model.stationDetails.Station;
import bsuir.repository.stationDetails.StationRepository;
import bsuir.service.StationDetails.StationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station save(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public Station getById(long id) {
        return stationRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        stationRepository.deleteById(id);
    }

    @Override
    public List<Station> getAllByCity(long city) {
        return stationRepository.findAllByCity(city);
    }

    @Override
    public List<Station> getAll() {
        return stationRepository.findAll();
    }

    @Override
    public List<Station> getAllByIdIn(List<Long> ids) {
        return stationRepository.findAllById(ids);
    }
}
