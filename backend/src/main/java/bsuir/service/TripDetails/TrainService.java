package bsuir.service.TripDetails;

import bsuir.model.stationDetails.Train;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TrainService {
    Train save(Train train);

    void delete(long id);

    Train getById(long id);

    List<Train> getAll();

    List<Train> getAllByModel(long model);
}
