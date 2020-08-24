package bsuir.controller.stationDetails;

import bsuir.model.pageModel.TripPage;
import bsuir.model.stationDetails.Trip;
import bsuir.model.viewModel.TripRecord;
import bsuir.service.StationDetails.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Trip> save(@RequestBody Trip trip){
        log.info("POST request [{URL: " +"/api/trips/save},{body:" + trip +"}];");
        return ok(tripService.save(trip));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id){
        log.info("DELETE request [{URL: " +"/api/trips},{parameter:" + id +"}];");
        tripService.delete(id);
        return ok("deleted");
    }

    @RequestMapping(value = "/get-all/by-arrival-station",method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllByArrivalStation(@RequestParam("arrivalStation") long arrivalStation){
        log.info("GET request [{URL: " +"/api/trips/get-all/by-arrival-station},{parameter:" + arrivalStation +"}];");
        return ok(tripService.getAllByArrivalStation(arrivalStation));
    }

    @RequestMapping(value = "/get-all/by-departure-station",method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllByDepartureStation(@RequestParam("departureStation") long departureStation){
        log.info("GET request [{URL: " +"/api/trips/get-all/by-departure-station},{parameter:" + departureStation +"}];");
        return ok(tripService.getAllByDepartureStation(departureStation));
    }

    @RequestMapping(value = "/get-all/by-train",method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getAllByTrain(@RequestParam("train") long train){
        log.info("GET request [{URL: " +"/api/trips/get-all/by-train},{parameter:" + train +"}];");
        return ok(tripService.getAllByTrain(train));
    }

    @RequestMapping(value = "/get-page",method = RequestMethod.GET)
    public ResponseEntity<TripPage> getPage(@RequestParam("page") int page,
                                            @RequestParam("size") int size,
                                            @RequestParam(value = "direction",defaultValue = "true") boolean direction,
                                            @RequestParam(value = "parameter",defaultValue = "idTrip") String parameter){
        log.info("GET request [{URL: " +"/api/trips/get-page}];");
        return ok(tripService.getPage(page,size,direction,parameter));
    }

    @RequestMapping(value = "/get-all/trip-record",method = RequestMethod.GET)
    public ResponseEntity<List<TripRecord>> getAllTripRecords(){
        log.info("GET request [{URL: " +"/api/trips/get-all/trip-record}];");
        return ResponseEntity.ok(tripService.getAllTripRecords());
    }

    @RequestMapping(value = "/get-limit",method = RequestMethod.GET)
    public ResponseEntity<List<TripRecord>> getTripRecordsLimitOrderedByCountry(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "5") int size,
            @RequestParam(value = "direction", defaultValue = "true") boolean direction,
            @RequestParam(value = "parameter",defaultValue = "idTrip") String parameter
    ){
        return ResponseEntity.ok(tripService.getPage(page,size,direction,parameter).getTripRecords());
    }
}
