package bsuir.service.taskDetails;

import bsuir.model.taskDetails.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);

    void delete(long id);

    Task getById(long id);

    List<Task> getAllByTaskCreator(long taskCreator);

    List<Task> getPageSorted(int page, int size, String parameter, boolean direction);

    List<Task> getPageByTaskNameSorted(int page, int size, String parameter, boolean direction,String search);

    List<Task> getPageSorted(int page, int size, String parameter, boolean direction, String search);
}
