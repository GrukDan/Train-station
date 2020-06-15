package bsuir.service.taskDetails.Impl;

import bsuir.model.taskDetails.Task;
import bsuir.repository.taskDetails.TaskRepository;
import bsuir.service.taskDetails.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Override
    public List<Task> getAllByTaskCreator(long taskCreator) {
        return taskRepository.findAllByTaskCreator(taskCreator);
    }

    @Override
    public List<Task> getPageSorted(int page, int size, String parameter, boolean direction) {
        return null;
    }

    @Override
    public List<Task> getPageByTaskNameSorted(int page, int size, String parameter, boolean direction, String search) {
        return null;
    }

    @Override
    public List<Task> getPageSorted(int page, int size, String parameter, boolean direction, String search) {
        return direction ?
                taskRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, parameter))).getContent()
                : taskRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, parameter))).getContent();
    }
}
