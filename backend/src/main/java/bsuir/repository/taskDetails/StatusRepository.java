package bsuir.repository.taskDetails;

import bsuir.model.taskDetails.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<Status,Long> {
}
