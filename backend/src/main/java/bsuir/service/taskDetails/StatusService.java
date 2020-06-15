package bsuir.service.taskDetails;

import bsuir.model.taskDetails.Status;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StatusService {
    Status getById(long id);

    List<Status> getAll();
}
