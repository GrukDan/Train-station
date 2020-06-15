package bsuir.repository.stationDetails;

import bsuir.model.stationDetails.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    Page<Country> findAllByCountry(PageRequest of);
}
