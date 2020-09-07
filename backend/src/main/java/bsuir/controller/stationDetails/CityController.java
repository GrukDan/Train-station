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

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<City> save(@RequestBody City city){
        log.info("POST request [{URL: " +"/api/cities/save},{body:" + city +"}];");
        return ResponseEntity.ok(cityService.save(city));
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        log.info("DELETE request [{URL: " +"/api/cities},{parameter:" + id +"}];");
        cityService.delete(id);
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<City>> getAll(){
        log.info("GET request [{URL: " +"/api/cities/get-all}];");
        return ResponseEntity.ok(cityService.getAll());
    }

    @RequestMapping(value = "/by-country",method = RequestMethod.GET)
    public ResponseEntity<List<City>> getAllByCountry(@RequestParam("country") Long country){
        log.info("GET request [{URL: " +"/api/cities/by-country},{parameter:" + country +"}];");
        return ResponseEntity.ok(cityService.getAllByCountry(country));
    }
}
