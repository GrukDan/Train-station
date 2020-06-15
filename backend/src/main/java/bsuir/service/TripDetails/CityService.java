package bsuir.service.TripDetails;

import bsuir.model.stationDetails.City;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CityService {
    City save(City city);

    void delete(long id);

    City getById(long id);

    List<City> getAllByCountry(long country);

    List<City> getPageSorted(int page, int size, String parameter, boolean direction,String search);
}
