package bsuir.service.taskDetails.Impl;

import bsuir.model.taskDetails.Status;
import bsuir.repository.taskDetails.StatusRepository;
import bsuir.service.taskDetails.StatusService;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status getById(long id) throws NotFoundException {
        return  statusRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Status> getAll() {
        return (List<Status>) statusRepository.findAll();
    }
}
