package bsuir.controller.stationDetails;


import bsuir.model.stationDetails.Station;
import bsuir.service.TripDetails.StationService;
import bsuir.service.taskDetails.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Station> save(@RequestBody Station station){
        return ResponseEntity.ok(stationService.save(station));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id){
        stationService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<Station>> getAllByCity(@RequestParam("city") long city){
        return ResponseEntity.ok(stationService.getAllByCity(city));
    }

}
