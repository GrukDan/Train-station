package bsuir.service.taskDetails.Impl;

import bsuir.model.taskDetails.Status;
import bsuir.repository.taskDetails.StatusRepository;
import bsuir.service.taskDetails.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status getById(long id) {
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public List<Status> getAll() {
        return (List<Status>) statusRepository.findAll();
    }
}
