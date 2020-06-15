package bsuir.service.TripDetails;

import bsuir.model.stationDetails.Country;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CountryService {
    Country save(Country country);

    void delete(long id);

    Country getById(long id);

    List<Country> getAll();

    List<Country> getPageSorted(int page, int size, String parameter, boolean direction,String search);
}
