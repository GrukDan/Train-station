package bsuir.service.StationDetails;

import bsuir.model.stationDetails.Country;

import java.util.List;
import java.util.Optional;


public interface CountryService {
    Country save(Country country);

    Country findByCountry(String country);

    void delete(long id);

    Country getById(long id);

    List<Country> getAll();

    List<Country> getPageSorted(int page, int size, String parameter, boolean direction,String search);
}
