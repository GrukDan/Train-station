package bsuir.service.taskDetails.Impl;

import bsuir.model.taskDetails.Rating;
import bsuir.repository.taskDetails.RatingRepository;
import bsuir.service.taskDetails.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> saveAll(List<Rating> ratings) {
        return ratingRepository.saveAll(ratings);
    }

    @Override
    public void delete(long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public Rating getById(long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public List<Rating> getByAlternativeIn(List<Long> alternatives) {
        return null;
    }

    @Override
    public List<Rating> getByExpert(long expert) {
        return ratingRepository.findAllByExpert(expert);
    }
}
