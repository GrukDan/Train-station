package bsuir.controller.stationDetails;

import bsuir.model.pageModel.TripPage;
import bsuir.model.stationDetails.Trip;
import bsuir.service.StationDetails.TripService;
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

    @RequestMapping(value = "/get-all/by-arrival-station",method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllByArrivalStation(@RequestParam("arrivalStation") long arrivalStation){
        return ResponseEntity.ok(tripService.getAllByArrivalStation(arrivalStation));
    }

    @RequestMapping(value = "/get-all/by-departure-station",method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllByDepartureStation(@RequestParam("departureStation") long departureStation){
        return ResponseEntity.ok(tripService.getAllByDepartureStation(departureStation));
    }

    @RequestMapping(value = "/get-all/by-train",method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllByTrain(@RequestParam("train") long train){
        return ResponseEntity.ok(tripService.getAllByTrain(train));
    }

    @RequestMapping(value = "/get-page",method = RequestMethod.GET)
    public ResponseEntity<TripPage> getPage(@RequestParam("page") int page,
                                            @RequestParam("size") int size,
                                            @RequestParam("direction") boolean direction,
                                            @RequestParam("parameter") String parameter){
        return ResponseEntity.ok(tripService.getPageSorted(page,size,direction,parameter));
    }
}
