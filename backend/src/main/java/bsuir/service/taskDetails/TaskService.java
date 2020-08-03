package bsuir.service.taskDetails;

import bsuir.model.pageModel.TaskPage;
import bsuir.model.taskDetails.Task;
import bsuir.model.viewModel.TaskViewModel;

import java.util.List;

public interface TaskService {
    Task save(Task task);

    void delete(long id);

    Task getById(long id);

    List<Task> getAllByTaskCreator(long taskCreator);

    TaskPage getPage(int page, int size, boolean direction, String parameter);

    List<String> getSortParameters();

    TaskViewModel getTaskViewModelById(long id);
}
