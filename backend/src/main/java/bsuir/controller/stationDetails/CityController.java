package bsuir.controller.stationDetails;


import bsuir.model.stationDetails.City;
import bsuir.service.StationDetails.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<City> save(@RequestBody City city){
        return ResponseEntity.ok(cityService.save(city));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id){
        cityService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<City>> getAll(){
        return ResponseEntity.ok(cityService.getAll());
    }

    @RequestMapping(value = "/by-country",method = RequestMethod.GET)
    public ResponseEntity<List<City>> getAllByCountry(@RequestParam("country") long country){
        return ResponseEntity.ok(cityService.getAllByCountry(country));
    }
}
