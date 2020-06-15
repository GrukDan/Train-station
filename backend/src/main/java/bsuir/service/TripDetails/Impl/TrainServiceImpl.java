package bsuir.service.TripDetails.Impl;

import bsuir.model.stationDetails.Train;
import bsuir.repository.stationDetails.TrainModelRepository;
import bsuir.repository.stationDetails.TrainRepository;
import bsuir.service.TripDetails.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train save(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public void delete(long id) {
        trainRepository.deleteById(id);
    }

    @Override
    public Train getById(long id) {
        return trainRepository.findById(id).orElse(null);
    }

    @Override
    public List<Train> getAll() {
        return (List<Train>) trainRepository.findAll();
    }

    @Override
    public List<Train> getAllByModel(long model) {
        return trainRepository.findAllByModel(model);
    }
}
