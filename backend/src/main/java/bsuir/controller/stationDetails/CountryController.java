package bsuir.controller.stationDetails;

import bsuir.model.stationDetails.Country;
import bsuir.service.TripDetails.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Country> save(@RequestBody Country country){
        return ResponseEntity.ok(countryService.save(country));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam("id") long id){
        countryService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getAll(){
        return ResponseEntity.ok(countryService.getAll());
    }

}
