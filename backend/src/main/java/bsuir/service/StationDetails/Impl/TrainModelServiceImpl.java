package bsuir.service.StationDetails.Impl;

import bsuir.model.stationDetails.TrainModel;
import bsuir.repository.stationDetails.TrainModelRepository;
import bsuir.service.StationDetails.TrainModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainModelServiceImpl implements TrainModelService {

    @Autowired
    private TrainModelRepository trainModelRepository;

    @Override
    public TrainModel save(TrainModel trainModel) {
        return trainModelRepository.save(trainModel);
    }

    @Override
    public void delete(long id) {
        trainModelRepository.deleteById(id);
    }

    @Override
    public TrainModel getById(long id) {
        return trainModelRepository.findById(id).orElse(null);
    }

    @Override
    public List<TrainModel> getAll() {
        return (List<TrainModel>) trainModelRepository.findAll();
    }
}
