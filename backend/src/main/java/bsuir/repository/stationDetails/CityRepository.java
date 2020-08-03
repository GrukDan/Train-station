package bsuir.repository.stationDetails;

import bsuir.model.stationDetails.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    List<City> findAllByCountry(long country);

    Page<City> findAllByCity(PageRequest of);
}
