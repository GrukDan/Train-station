package bsuir.service.StationDetails.Impl;

import bsuir.exception.AlreadyExists;
import bsuir.model.pageModel.TripPage;
import bsuir.model.stationDetails.*;
import bsuir.model.viewModel.TripRecord;
import bsuir.repository.stationDetails.TripRepository;
import bsuir.service.StationDetails.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    @Setter(onMethod = @__({@Autowired}))
    private TripRepository tripRepository;

    @Setter(onMethod = @__({@Autowired}))
    private CountryService countryService;

    @Setter(onMethod = @__({@Autowired}))
    private CityService cityService;

    @Setter(onMethod = @__({@Autowired}))
    private StationService stationService;

    @Setter(onMethod = @__({@Autowired}))
    private TrainService trainService;

    @Setter(onMethod = @__({@Autowired}))
    private TrainModelService trainModelService;

    private class TripStationData {
        List<Station> stations;
        List<City> cities;
        List<Country> countries;
    }

    private boolean tripExists(Trip trip) {
        return tripRepository
                .existsByDepartureStationAndArrivalStationAndTrain(
                        trip.getDepartureStation(),
                        trip.getArrivalStation(),
                        trip.getTrain());
    }

    @Override
    public Trip save(Trip trip) throws AlreadyExists {
        if (!tripExists(trip)) {
            return tripRepository.save(trip);
        } else throw new AlreadyExists("The some trip" + trip.toString() + "already exists.");
    }

    @Override
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public Trip getById(Long id) throws ChangeSetPersister.NotFoundException {
        return tripRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    @Override
    public List<Trip> getAllByDepartureStation(Long departureStation) {
        return tripRepository.findAllByDepartureStation(departureStation);
    }

    @Override
    public List<Trip> getAllByArrivalStation(Long arrivalStation) {
        return tripRepository.findAllByArrivalStation(arrivalStation);
    }

    @Override
    public List<Trip> getAllByDepartureStationAndArrivalStation(Long departureStation, Long arrivalStation) {
        return tripRepository.findAllByDepartureStationAndArrivalStation(departureStation, arrivalStation);
    }

    @Override
    public List<Trip> getAllByTrain(Long train) {
        return tripRepository.findAllByTrain(train);
    }

    @Override
    public TripPage getPage(int page, int size, boolean direction, String parameter) {
        Page<Trip> tripsPage = getPageSorted(page, size, parameter, direction);
        List<TripRecord> tripRecords = buildTripRecords(tripsPage.getContent());
        return new TripPage(page, size, tripsPage.getTotalElements(), direction, parameter, tripRecords);
    }

    private List<TripRecord> buildTripRecords(List<Trip> trips) {
        List<TripRecord> tripRecords = new ArrayList<>();
        trips.forEach(trip -> {
            tripRecords.add(new TripRecord(trip));
        });
        setArrivalData(tripRecords);
        setDepartureData(tripRecords);
        setTrainData(tripRecords);
        return tripRecords;
    }

    private void setTrainData(List<TripRecord> tripRecords) {
        List<Train> trains = trainService.getAllByIdIn(collectTrainIds(tripRecords));
        List<TrainModel> trainModels = trainModelService.getAllByIdIn(collectTrainModelIds(trains));

        setTrain(tripRecords, trains);
        setTrainModel(tripRecords, trainModels);
    }

    private TripStationData getTripStationData(List<Long> stationIds) {
        TripStationData tripStationData = new TripStationData();
        tripStationData.stations = stationService.getAllByIdIn(stationIds);
        tripStationData.cities = cityService.getAllByIdIn(collectCity(tripStationData.stations));
        tripStationData.countries = countryService.getAllByIdIn(collectCountry(tripStationData.cities));
        return tripStationData;
    }

    private void setArrivalData(List<TripRecord> tripRecords) {
        TripStationData arrivalData = getTripStationData(collectArrivalStationIds(tripRecords));

        setArrivalStation(tripRecords, arrivalData.stations);
        setArrivalCity(tripRecords, arrivalData.cities);
        setArrivalCountry(tripRecords, arrivalData.countries);
    }

    private void setDepartureData(List<TripRecord> tripRecords) {
        TripStationData arrivalData = getTripStationData(collectDepartureStationIds(tripRecords));

        setDepartureStation(tripRecords, arrivalData.stations);
        setDepartureCity(tripRecords, arrivalData.cities);
        setDepartureCountry(tripRecords, arrivalData.countries);
    }

    private List<Long> collectTrainIds(List<TripRecord> tripRecords) {
        return tripRecords
                .stream()
                .map(Trip::getTrain)
                .collect(Collectors.toList());
    }

    private List<Long> collectTrainModelIds(List<Train> trains) {
        return trains
                .stream()
                .map(Train::getModel)
                .collect(Collectors.toList());
    }

    private List<Long> collectDepartureStationIds(List<TripRecord> tripRecords) {
        return tripRecords
                .stream()
                .map(Trip::getDepartureStation)
                .collect(Collectors.toList());
    }

    private List<Long> collectArrivalStationIds(List<TripRecord> tripRecords) {
        return tripRecords
                .stream()
                .map(Trip::getArrivalStation)
                .collect(Collectors.toList());
    }

    private List<Long> collectCity(List<Station> stations) {
        return stations
                .stream()
                .map(Station::getCity)
                .collect(Collectors.toList());
    }

    private List<Long> collectCountry(List<City> cities) {
        return cities
                .stream()
                .map(City::getCountry)
                .collect(Collectors.toList());
    }

    private void setTrain(List<TripRecord> tripRecords, List<Train> trains) {
        tripRecords.forEach(tripRecord ->
        {
            try {
                tripRecord.setTrainObj(
                        trains
                                .stream()
                                .filter(train -> train.getIdTrain() == tripRecord.getTrain())
                                .findFirst()
                                .orElseThrow(ChangeSetPersister.NotFoundException::new));
            } catch (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setTrainModel(List<TripRecord> tripRecords, List<TrainModel> trainModels) {
        tripRecords.forEach(tripRecord ->
        {
            try {
                tripRecord.setTrainModelObj(
                        trainModels
                                .stream()
                                .filter(trainModel -> trainModel.getIdTrainModel() == tripRecord
                                        .getTrainObj()
                                        .getModel())
                                .findFirst()
                                .orElseThrow(ChangeSetPersister.NotFoundException::new));
            } catch (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setDepartureStation(List<TripRecord> tripRecords, List<Station> stations) {
        tripRecords.forEach(tripRecord -> {
            try {
                tripRecord.setDepartureStationObj(
                        stations
                                .stream()
                                .filter(station -> station.getIdStation() == tripRecord.getDepartureStation())
                                .findFirst()
                                .orElseThrow(ChangeSetPersister.NotFoundException::new)
                );
            } catch (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setArrivalStation(List<TripRecord> tripRecords, List<Station> stations) {
        tripRecords.forEach(tripRecord -> {
            try {
                tripRecord.setArrivalStationObj(
                        stations
                                .stream()
                                .filter(station -> station.getIdStation() == tripRecord.getArrivalStation())
                                .findFirst()
                                .orElseThrow(ChangeSetPersister.NotFoundException::new)
                );
            } catch (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setArrivalCity(List<TripRecord> tripRecords, List<City> cities) {
        tripRecords.forEach(tripRecord -> {
            try {
                tripRecord.setArrivalCityObj(
                        cities
                                .stream()
                                .filter(city ->
                                        city.getIdCity() == tripRecord.getArrivalStationObj().getCity())
                                .findFirst()
                                .orElseThrow(ChangeSetPersister.NotFoundException::new)
                );
            } catch (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setDepartureCity(List<TripRecord> tripRecords, List<City> cities) {
        tripRecords.forEach(tripRecord -> {
            try {
                tripRecord.setDepartureCityObj(
                        cities
                                .stream()
                                .filter(city ->
                                        city.getIdCity() == tripRecord.getDepartureStationObj().getCity())
                                .findFirst()
                                .orElseThrow(ChangeSetPersister.NotFoundException::new)
                );
            } catch (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setArrivalCountry(List<TripRecord> tripRecords, List<Country> countries) {
        tripRecords.forEach(tripRecord -> {
            try {
                tripRecord.setArrivalCountryObj(
                        countries
                                .stream()
                                .filter(country ->
                                        country.getIdCountry() == tripRecord.getArrivalCityObj().getCountry())
                                .findFirst()
                                .orElseThrow(ChangeSetPersister.NotFoundException::new)
                );
            } catch (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setDepartureCountry(List<TripRecord> tripRecords, List<Country> countries) {
        tripRecords.forEach(tripRecord -> {
            try {
                tripRecord.setDepartureCountryObj(
                        countries
                                .stream()
                                .filter(country ->
                                        country.getIdCountry() == tripRecord.getDepartureCityObj().getCountry())
                                .findFirst()
                                .orElseThrow(ChangeSetPersister.NotFoundException::new)
                );
            } catch (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private Page<Trip> getPageSorted(int page, int size, String parameter, boolean direction) {
        return direction ?
                tripRepository.findAll(PageRequest.of(page, size))
                : tripRepository.findAll(PageRequest.of(page, size));
    }


    @Override
    public List<TripRecord> getAllTripRecords() {
        return null;
    }

}
