package bsuir.service.taskDetails.Impl;

import bsuir.model.taskDetails.Alternative;
import bsuir.service.taskDetails.AlternativeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlternativeServiceImpl implements AlternativeService {
    @Override
    public Alternative save(Alternative alternative) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Alternative getById(long id) {
        return null;
    }

    @Override
    public List<Alternative> getAlternativesByTask(long task) {
        return null;
    }

    @Override
    public List<Alternative> getAlternativesByTrip(long trip) {
        return null;
    }
}
