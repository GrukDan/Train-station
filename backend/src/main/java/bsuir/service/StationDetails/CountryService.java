package bsuir.service.StationDetails;

import bsuir.model.stationDetails.Country;

import java.util.List;


public interface CountryService {
    Country save(Country country);

    Country findByCountry(String country);

    void delete(long id);

    Country getById(long id);

    List<Country> getAll();

    List<Country> getPageSorted(int page, int size, String parameter, boolean direction,String search);

    List<Country> getAllByIdIn(List<Long> ids);
}
