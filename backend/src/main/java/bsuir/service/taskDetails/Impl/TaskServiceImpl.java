package bsuir.service.taskDetails.Impl;

import bsuir.model.pageModel.TaskPage;
import bsuir.model.taskDetails.Status;
import bsuir.model.taskDetails.Task;
import bsuir.model.userDetails.User;
import bsuir.model.viewModel.TaskViewModel;
import bsuir.passayGenerator.PassayGenerator;
import bsuir.repository.taskDetails.TaskRepository;
import bsuir.service.taskDetails.StatusService;
import bsuir.service.taskDetails.TaskService;
import bsuir.service.userDetails.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UserService userService;

    private String[] parameters;

    {
        parameters = new String[]{"taskName", "task_code", "status","date_of_creation", "task_creator"};
    }

    @Override
    public Task save(Task task) {
        PassayGenerator passayGenerator = new PassayGenerator();
        String code = task.getTaskName() + '-' +
                passayGenerator.generateAlphabetCode(4);
        task.setTaskCode(code);
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

    private Page<Task> getPageSorted(int page, int size, String parameter, boolean direction) {
        return direction ?
                taskRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, parameter)))
                : taskRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, parameter)));
    }

    @Override
    public TaskPage getPage(int page, int size, boolean direction, String parameter) {
        Page<Task> tasksPage = getPageSorted(page, size, parameter, direction);
        List<TaskViewModel> taskViewModels = buildPageContent(tasksPage.getContent());
        return new TaskPage(page, size, tasksPage.getTotalElements(), direction, parameter, taskViewModels);
    }

    private List<TaskViewModel> buildPageContent(List<Task> tasks) {
        List<TaskViewModel> taskViewModels = buildTaskViewModels(tasks);
        List<Long> taskCreatorIds = collectTaskCreatorIds(tasks);
        setTaskCreatorNames(taskViewModels, userService.getUsersById(taskCreatorIds));
        setStatusName(taskViewModels, statusService.getAll());
        return taskViewModels;
    }

    private List<Long> collectTaskCreatorIds(List<Task> tasks) {
        return tasks
                .stream()
                .map(Task::getTaskCreator)
                .collect(Collectors.toList());
    }

    private void setTaskCreatorNames(List<TaskViewModel> taskViewModels, List<User> taskCreators) {
        taskCreators.forEach(taskCreator -> {
            taskViewModels
                    .stream()
                    .filter(taskViewModel -> taskViewModel.getTaskCreator() == taskCreator.getIdUser())
                    .forEach(taskViewModel -> {
                        taskViewModel.setTaskCreatorName(taskCreator.getName());
                        taskViewModel.setTaskCreatorSurname(taskCreator.getSurname());
                    });
        });
    }

    private List<TaskViewModel> buildTaskViewModels(List<Task> tasks) {
        List<TaskViewModel> taskViewModels = new ArrayList<>();
        tasks.forEach(task -> {
            TaskViewModel taskViewModel = new TaskViewModel(task);
            taskViewModel.getUsers().forEach(User::forgetPassword);
            taskViewModels.add(taskViewModel);
        });
        return taskViewModels;
    }

    private TaskViewModel buildTaskViewModel(Task task) throws ChangeSetPersister.NotFoundException {
        TaskViewModel taskViewModel = new TaskViewModel(task);
        setStatusName(taskViewModel,statusService);
        setTaskCreatorNameAndSurname(taskViewModel,userService);
        return taskViewModel;
    }

    private void setStatusName(TaskViewModel taskViewModel,StatusService statusService) throws ChangeSetPersister.NotFoundException {
        Status status = statusService.getById(taskViewModel.getStatus());
        taskViewModel.setStatusName(status.getStatus());
    }

    private void setTaskCreatorNameAndSurname(TaskViewModel taskViewModel,UserService userService) throws ChangeSetPersister.NotFoundException {
        User user = userService.getById(taskViewModel.getTaskCreator());
        taskViewModel.setTaskCreatorName(user.getName());
        taskViewModel.setTaskCreatorSurname(user.getSurname());
    }

    private void setStatusName(List<TaskViewModel> taskViewModels, List<Status> statuses) throws NullPointerException {
        taskViewModels
                .forEach(taskViewModel -> taskViewModel.setStatusName(statuses
                        .stream()
                        .findFirst().orElseThrow(NullPointerException::new).getStatus()));
    }

    @Override
    public List<String> getSortParameters() {
        return Arrays.asList(parameters);
    }

    @Override
    public TaskViewModel getTaskViewModelById(long id) {
        TaskViewModel taskViewModel = null;
        try {
            Task task = taskRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            taskViewModel = buildTaskViewModel(task);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
        return taskViewModel;
    }
}
