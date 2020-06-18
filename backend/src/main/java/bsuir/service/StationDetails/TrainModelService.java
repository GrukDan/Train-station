package bsuir.service.StationDetails;

import bsuir.model.stationDetails.TrainModel;

import java.util.List;


public interface TrainModelService {
    TrainModel save(TrainModel trainModel);

    void delete(long id);

    TrainModel getById(long id);

    List<TrainModel> getAll();
}
