package bsuir.service.StationDetails.Impl;

import bsuir.model.stationDetails.Country;
import bsuir.repository.stationDetails.CountryRepository;
import bsuir.service.StationDetails.CountryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void delete(long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Country findByCountry(String country) {
        return countryRepository.findByCountry(country).orElse(null);
    }

    @Override
    public Country getById(long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> getPageSorted(int page, int size, String parameter, boolean direction, String search) {
        return direction ?
                countryRepository.findAllByCountry(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, search))).getContent()
                : countryRepository.findAllByCountry(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, search))).getContent();
    }

    @Override
    public List<Country> getAllByIdIn(List<Long> ids) {
        return countryRepository.findAllById(ids);
    }

    @Override
    public List<Country> getLimitOrderedByCountry(int limit) {
        return countryRepository
                .findAll(PageRequest.of(0,limit, Sort.by(Sort.Direction.ASC,"country")))
                .getContent();
    }
}
