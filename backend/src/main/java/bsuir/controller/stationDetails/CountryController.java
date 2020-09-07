package bsuir.controller.stationDetails;

import bsuir.model.stationDetails.Country;
import bsuir.service.StationDetails.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Country> save(@RequestBody Country country){
        log.info("POST request [{URL: " +"/api/countries/save},{body:" + country +"}];");
        return ResponseEntity.ok(countryService.save(country));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        log.info("DELETE request [{URL: " +"/api/countries},{parameter:" + id +"}];");
        countryService.delete(id);
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getAll(){
        log.info("DELETE request [{GET: " +"/api/countries/get-all}];");
        return ResponseEntity.ok(countryService.getAll());
    }

}
