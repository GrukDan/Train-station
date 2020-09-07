package bsuir.model.viewModel;

import bsuir.model.stationDetails.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRecord extends Trip{
    private Country departureCountryObj;
    private Country arrivalCountryObj;
    private City departureCityObj;
    private City arrivalCityObj;
    private Station departureStationObj;
    private Station arrivalStationObj;
    private TrainModel trainModelObj;
    private Train trainObj;

    public TripRecord(Trip trip){
        super(trip);
    }
}
