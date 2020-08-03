package bsuir.repository.stationDetails;

import bsuir.model.stationDetails.TrainModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainModelRepository extends JpaRepository<TrainModel,Long> {
}
