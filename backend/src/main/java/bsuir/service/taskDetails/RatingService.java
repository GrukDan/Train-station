package bsuir.service.taskDetails;

import bsuir.model.taskDetails.Alternative;
import bsuir.model.taskDetails.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {
    Rating save(Rating rating);

    List<Rating> saveAll(List<Rating> ratings);

    void delete(long id);

    Rating getById(long id);

    List<Rating> getByAlternativeIn(List<Long> alternatives);

    List<Rating> getByExpert(long expert);

}
