import {Country} from "../station-details/country";
import {City} from "../station-details/city";
import {Station} from "../station-details/station";
import {Train} from "../station-details/train";
import {TrainModel} from "../station-details/train-model";
import {Trip} from "../station-details/trip";


export class TripRecord extends Trip {
  departureCountryObj: Country;
  arrivalCountryObj: Country;
  departureCityObj: City;
  arrivalCityObj: City;
  departureStationObj: Station;
  arrivalStationObj: Station;
  trainModelObj: TrainModel;
  trainObj: Train;
}
