package bsuir.controller.taskDetails;


import bsuir.model.pageModel.TaskPage;
import bsuir.model.taskDetails.Task;
import bsuir.model.viewModel.TaskViewModel;
import bsuir.service.taskDetails.StatusService;
import bsuir.service.taskDetails.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/save",method = RequestMethod.PUT)
    private ResponseEntity<Task> save(@RequestBody Task task){
        log.info("PUT request [{URL: " +"/api/tasks/save}" +" ,{body: " + task + "}];");
        try {
            task = taskService.save(task);
        } catch (ChangeSetPersister.NotFoundException exception) {
            exception.printStackTrace();
        }
        return task!=null
                ? ResponseEntity.created(URI.create("/api/tasks/save")).body(task)
                : ResponseEntity.badRequest().body(new Task());
    }

    @RequestMapping(value = "/get-parameters", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getSortParameters() {
        log.info("GET request [{URL: " +"/api/tasks/get-parameters}];");
        return ResponseEntity.ok(taskService.getSortParameters());
    }

    @RequestMapping(value = "/get-page", method = RequestMethod.GET)
    public ResponseEntity<TaskPage> getPageSorted(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("parameter") String parameter,
            @RequestParam("direction") boolean direction) {
        log.info("GET request [{URL: " +"/api/tasks/get-page}];");
        return ResponseEntity.ok(taskService.getPage(page, size, direction, parameter));
    }

    @RequestMapping(value = "/get-by-id",method = RequestMethod.GET)
    public ResponseEntity<TaskViewModel> getTaskViewModelById(@RequestParam("id") long id){
        log.info("GET request [{URL: " +"/api/tasks/get-by-id}];");
        TaskViewModel taskViewModel = taskService.getTaskViewModelById(id);
        return taskViewModel!=null
                ? ResponseEntity.ok(taskViewModel)
                : ResponseEntity.notFound().build();
    }
}
