package bsuir.controller.stationDetails;


import bsuir.model.stationDetails.City;
import bsuir.service.StationDetails.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<City> save(@RequestBody City city){
        log.info("POST request [{URL: " +"/api/cities/save},{body:" + city +"}];");
        return ResponseEntity.ok(cityService.save(city));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id){
        log.info("DELETE request [{URL: " +"/api/cities},{parameter:" + id +"}];");
        cityService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<City>> getAll(){
        log.info("GET request [{URL: " +"/api/cities/get-all}];");
        return ResponseEntity.ok(cityService.getAll());
    }

    @RequestMapping(value = "/by-country",method = RequestMethod.GET)
    public ResponseEntity<List<City>> getAllByCountry(@RequestParam("country") long country){
        log.info("GET request [{URL: " +"/api/cities/by-country},{parameter:" + country +"}];");
        return ResponseEntity.ok(cityService.getAllByCountry(country));
    }
}
