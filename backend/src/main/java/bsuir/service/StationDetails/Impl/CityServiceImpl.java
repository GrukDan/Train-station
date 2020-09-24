package bsuir.service.StationDetails.Impl;

import bsuir.model.stationDetails.City;
import bsuir.repository.stationDetails.CityRepository;
import bsuir.service.StationDetails.CityService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void delete(long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public City getById(long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public List<City> getAllByCountry(long country) {
        return cityRepository.findAllByCountry(country);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public List<City> getAllByIdIn(List<Long> ids) {
        return cityRepository.findAllById(ids);
    }

    @Override
    public List<City> getPageSorted(int page, int size, String parameter, boolean direction, String search) {
        return direction ?
                cityRepository.findAllByCity(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, search))).getContent()
                : cityRepository.findAllByCity(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, search))).getContent();

    }
}
