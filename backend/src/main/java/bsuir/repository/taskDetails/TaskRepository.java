package bsuir.repository.taskDetails;

import bsuir.model.taskDetails.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByTaskCreator(long taskCreator);

    Page<Task> findAllByTaskName(String taskName, PageRequest pageRequest);

    Optional<Task> findByTaskCode(String taskCode);
}
