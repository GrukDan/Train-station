package bsuir.controller.stationDetails;

import bsuir.model.stationDetails.Train;
import bsuir.service.StationDetails.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Train> save(@RequestBody Train train){
        return ResponseEntity.ok(trainService.save(train));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id){
        trainService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/get-all/by-model",method = RequestMethod.GET)
    public ResponseEntity<List<Train>> getAllByModel(@RequestParam("model") long model){
        return ResponseEntity.ok(trainService.getAllByModel(model));
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<Train>> getAll(){
        return ResponseEntity.ok(trainService.getAll());
    }
}
