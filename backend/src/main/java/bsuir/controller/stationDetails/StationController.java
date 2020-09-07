package bsuir.controller.stationDetails;


import bsuir.model.stationDetails.Station;
import bsuir.service.StationDetails.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Station> save(@RequestBody Station station){
        log.info("POST request [{URL: " +"/api/stations/save},{body:" + station +"}];");
        return ResponseEntity.ok(stationService.save(station));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        log.info("DELETE request [{URL: " +"/api/stations},{parameter:" + id +"}];");
        stationService.delete(id);
    }

    @RequestMapping(value = "/by-city",method = RequestMethod.GET)
    public ResponseEntity<List<Station>> getAllByCity(@RequestParam("city") Long city){
        log.info("GET request [{URL: " +"/api/stations/by-city},{parameter:" + city +"}];");
        return ResponseEntity.ok(stationService.getAllByCity(city));
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<Station>> getAll(){
        log.info("GET request [{URL: " +"/api/stations/get-all}];");
        return ResponseEntity.ok(stationService.getAll());
    }

}
