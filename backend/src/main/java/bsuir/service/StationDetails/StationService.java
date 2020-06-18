package bsuir.service.StationDetails;

import bsuir.model.stationDetails.Station;

import java.util.List;


public interface StationService {

    Station save(Station station);

    Station getById(long id);

    void delete(long id);

    List<Station> getAllByCity(long city);

}
