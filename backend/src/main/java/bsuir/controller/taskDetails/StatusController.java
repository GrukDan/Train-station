package bsuir.controller.taskDetails;


import bsuir.model.taskDetails.Status;
import bsuir.service.taskDetails.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<Status>> getAll(){
        return ResponseEntity.ok(statusService.getAll());
    }
}
