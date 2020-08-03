package bsuir.repository.stationDetails;

import bsuir.model.stationDetails.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train,Long> {
    List<Train> findAllByModel(long model);

}
