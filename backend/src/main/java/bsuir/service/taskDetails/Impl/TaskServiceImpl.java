package bsuir.service.taskDetails.Impl;

import bsuir.model.pageModel.TaskPage;
import bsuir.model.taskDetails.Status;
import bsuir.model.taskDetails.Task;
import bsuir.model.userDetails.User;
import bsuir.model.viewModel.TaskViewModel;
import bsuir.sequenceGenerator.PassayGenerator;
import bsuir.repository.taskDetails.TaskRepository;
import bsuir.service.taskDetails.StatusService;
import bsuir.service.taskDetails.TaskService;
import bsuir.service.userDetails.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final StatusService statusService;
    private final UserService userService;
    private final PassayGenerator passayGenerator;

    private final String[] parameters;

    {
        parameters = new String[]{"taskName", "taskCode", "status","dateOfCreation", "taskCreator"};
    }

    public TaskServiceImpl(TaskRepository taskRepository, StatusService statusService, UserService userService, PassayGenerator passayGenerator) {
        this.taskRepository = taskRepository;
        this.statusService = statusService;
        this.userService = userService;
        this.passayGenerator = passayGenerator;
    }

    private void setTaskCode(Task task){
        String taskCode = task.getTaskName() + '-' +
                passayGenerator.generateAlphabetCode(4);
        task.setTaskCode(taskCode);
    }

    @Override
    public Task save(Task task) throws ChangeSetPersister.NotFoundException {
        setTaskCode(task);
        List<User> users = userService.getUsersById(task.collectUserIds());
        taskRepository.save(task);
        Task savedTask = taskRepository
                .findByTaskCode(task.getTaskCode())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        Set<Task> tasks = new HashSet();
        tasks.add(savedTask);
        users.forEach(user -> {
            user.setTasks(tasks);
        });
        userService.saveAll(users);
        return savedTask;
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
        return  TaskPage.builder()
                .page(page)
                .size(size)
                .totalElements(tasksPage.getTotalElements())
                .direction(direction)
                .parameter(parameter)
                .taskViewModels(taskViewModels)
                .build();
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
