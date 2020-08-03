package bsuir.controller.stationDetails;

import bsuir.model.stationDetails.TrainModel;
import bsuir.service.StationDetails.TrainModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/train-models")
public class TrainModelController {

    @Autowired
    private TrainModelService trainModelService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<TrainModel> save(@RequestBody TrainModel trainModel){
        log.info("POST request [{URL: " +"/api/train-models/save},{body:" + trainModel +"}];");
        return ResponseEntity.ok(trainModelService.save(trainModel));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id){
        log.info("DELETE request [{URL: " +"/api/train-models},{parameter:" + id +"}];");
        trainModelService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<TrainModel>> getAll(){
        log.info("GET request [{URL: " +"/api/train-models/get-all}];");
        return ResponseEntity.ok(trainModelService.getAll());
    }
}
