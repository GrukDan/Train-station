package bsuir.repository.taskDetails;

import bsuir.model.taskDetails.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
//    List<Rating> findByAlternative2InOrAlternative2In(Collection<Long> alternatives);

    List<Rating> findAllByExpert(long expert);

}
