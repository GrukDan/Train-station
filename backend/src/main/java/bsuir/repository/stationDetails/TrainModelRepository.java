package bsuir.repository.stationDetails;

import bsuir.model.stationDetails.TrainModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainModelRepository extends CrudRepository<TrainModel,Long> {
}
