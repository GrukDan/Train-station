package bsuir.repository.stationDetails;

import bsuir.model.stationDetails.Train;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends CrudRepository<Train,Long> {
    List<Train> findAllByModel(long model);

}
