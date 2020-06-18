import {Country} from "../station-details/country";
import {City} from "../station-details/city";
import {Station} from "../station-details/station";
import {Train} from "../station-details/train";
import {TrainModel} from "../station-details/train-model";
import {Trip} from "../station-details/trip";

export class TripRecord {
  country:Country;
  city:City;
  station:Station;
  trainModel:TrainModel;
  train:Train;
  trip:Trip;

  constructor(){}

  // constructor() {
  //   this.country = new Country();
  //   this.city = new City();
  //   this.station = new Station();
  //   this.trainModel = new TrainModel();
  //   this.trip = new Trip();
  // }
}
