package bsuir.service.taskDetails;

import bsuir.model.taskDetails.Alternative;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AlternativeService {

    Alternative save(Alternative alternative);

    void delete(long id);

    Alternative getById(long id);

    List<Alternative> getAlternativesByTask(long task);

    List<Alternative> getAlternativesByTrip(long trip);
}
