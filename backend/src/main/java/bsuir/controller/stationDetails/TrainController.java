package bsuir.controller.stationDetails;

import bsuir.model.stationDetails.Train;
import bsuir.service.StationDetails.TrainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/trains")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Train> save(@RequestBody Train train){
        log.info("POST request [{URL: " +"/api/trains/save},{body:" + train +"}];");
        return ResponseEntity.ok(trainService.save(train));
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        log.info("DELETE request [{URL: " +"/api/trains},{parameter:" + id +"}];");
        trainService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/get-all/by-model/{model}",method = RequestMethod.GET)
    public ResponseEntity<List<Train>> getAllByModel(@PathVariable("model") Long model){
        log.info("GET request [{URL: " +"/api/trains/get-all/by-model},{parameter:" + model +"}];");
        return ResponseEntity.ok(trainService.getAllByModel(model));
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<Train>> getAll(){
        log.info("GET request [{URL: " +"/api/trains/get-all}];");
        return ResponseEntity.ok(trainService.getAll());
    }
}
