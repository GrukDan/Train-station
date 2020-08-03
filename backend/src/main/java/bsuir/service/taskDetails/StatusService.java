package bsuir.service.taskDetails;

import bsuir.model.taskDetails.Status;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


public interface StatusService {
    Status getById(long id) throws ChangeSetPersister.NotFoundException;

    List<Status> getAll();
}
