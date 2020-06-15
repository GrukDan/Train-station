package bsuir.controller.stationDetails;

import bsuir.model.stationDetails.Station;
import bsuir.model.stationDetails.Trip;
import bsuir.service.TripDetails.StationService;
import bsuir.service.TripDetails.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Trip> save(@RequestBody Trip trip){
        return ResponseEntity.ok(tripService.save(trip));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id){
        tripService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/get-all/by-station",method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllByStation(@RequestParam("station") long station){
        return ResponseEntity.ok(tripService.getAllByStation(station));
    }

    @RequestMapping(value = "/get-all/by-train",method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllByTrain(@RequestParam("train") long train){
        return ResponseEntity.ok(tripService.getAllByTrain(train));
    }
}
