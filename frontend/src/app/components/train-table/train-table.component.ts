import {Component, OnInit, TemplateRef} from '@angular/core';
import {ModalWindowService} from "../../services/modal-window/modal-window.service";
import {Country} from "../../models/station-details/country";
import {City} from "../../models/station-details/city";
import {Station} from "../../models/station-details/station";
import {TrainModel} from "../../models/station-details/train-model";
import {Train} from "../../models/station-details/train";
import {Trip} from "../../models/station-details/trip";
import {FormGroup} from "@angular/forms";
import {ValidationService} from "../../services/reactive-forms/validation.service";
import {BsLocaleService} from "ngx-bootstrap/datepicker";
import {BsDropdownConfig} from "ngx-bootstrap/dropdown";
import {CountryService} from "../../services/station-details/country.service";
import {CityService} from "../../services/station-details/city.service";
import {StationService} from "../../services/station-details/station.service";
import {TrainModelService} from "../../services/station-details/train-model.service";
import {TrainService} from "../../services/station-details/train.service";
import {TripService} from "../../services/station-details/trip.service";
import {Subscription} from "rxjs";
import {TripRecord} from "../../models/view-models/trip-record";
import {PageChangedEvent} from "ngx-bootstrap/pagination";


@Component({
  selector: 'app-train-table',
  templateUrl: './train-table.component.html',
  styleUrls: ['./train-table.component.css'],
  providers: [{provide: BsDropdownConfig, useValue: {isAnimated: true, autoClose: true}}]
})
export class TrainTableComponent implements OnInit {

  trainForm: FormGroup;

  country: Country;
  city: City;
  station: Station;
  trainModel: TrainModel;
  train: Train;
  trip: Trip;

  countries: Country[];
  cities: City[];
  trainModels: TrainModel[];
  stations: Station[];
  trains: Train[];

  arrivalCities: City[];
  departureCities: City[];
  arrivalStation: Station[];
  departureStation: Station[];

  idDepartureCity: number;
  idArrivalCity: number;

  tripRecords: TripRecord[];
  subscriptions: Subscription[];

  direction: boolean = false;
  parameters: string[];

  constructor(public modalWindowService: ModalWindowService,
              private validationService: ValidationService,
              private localeService: BsLocaleService,
              private countryService: CountryService,
              private cityService: CityService,
              private stationService: StationService,
              private trainModelService: TrainModelService,
              private trainService: TrainService,
              private tripService: TripService) {

    this.createValidationForm();

    this.country = new Country();
    this.city = new City();
    this.station = new Station();
    this.trainModel = new TrainModel();
    this.train = new Train();
    this.trip = new Trip();

    this.countries = [];
    this.cities = [];
    this.stations = [];
    this.trains = [];
    this.parameters = [];

    this.tripRecords = [];

    this.subscriptions = [];
  }

  ngOnInit() {
  }

  createValidationForm() {
    this.trainForm = this.validationService.getTrainFormGroup();
  }

  get _dateOfCreation() {
    return this.trainForm.get('dateOfCreation');
  }

  addCountry(country: Country) {
    this.subscriptions.push(this.countryService.save(country).subscribe(country => {
      this.modalWindowService.closeModal();
    }))
  }

  addCity(city: City, country: string) {
    city.country = parseInt(country);
    this.subscriptions.push(this.cityService.save(city).subscribe(country => {
      this.modalWindowService.closeModal();
    }))
  }

  addStation(station: Station, city: string) {
    station.city = parseInt(city);
    this.subscriptions.push(this.stationService.save(station).subscribe(country => {
      this.modalWindowService.closeModal();
    }))
  }

  addTrainModel(trainModel: TrainModel) {
    this.subscriptions.push(this.trainModelService.save(trainModel).subscribe(trainModel => {
      this.modalWindowService.closeModal();
    }))
  }

  loadAllTrainModel() {
    this.subscriptions.push(this.trainModelService.getAll().subscribe(trainModels => {
      this.trainModels = trainModels as TrainModel[];
    }))
  }

  loadAllCountries() {
    this.subscriptions.push(this.countryService.getAll().subscribe(countries => {
      this.countries = countries as Country[];
    }))
  }

  loadAllCitiesByCountry(country: any) {
    this.subscriptions.push(this.cityService.getAllByCountry(country).subscribe(cities => {
      this.cities = cities as City[];
    }))
  }

  openTripModal(newTrip: TemplateRef<any>) {
    this.modalWindowService.openModal(newTrip);
    this.loadAllCountries();
  }

  openTrainModal(newTrain: TemplateRef<any>) {
    this.loadAllTrainModel();
    this.modalWindowService.openModal(newTrain);
  }

  openTrainModelModal(newTrainModel: TemplateRef<any>) {
    this.modalWindowService.openModal(newTrainModel);
  }

  openStationModal(newStation: TemplateRef<any>) {
    this.modalWindowService.openModal(newStation);
  }

  openCityModal(newCity: TemplateRef<any>) {
    this.modalWindowService.openModal(newCity);
    this.loadAllCountries();
  }

  openCountryModal(newCountry: TemplateRef<any>) {
    this.modalWindowService.openModal(newCountry);
  }

  addTrip(trip: Trip) {
    this.subscriptions.push(this.tripService.save(trip).subscribe(trip => {
    }))
  }

  loadAllDepartureCitiesByCountry(country: any) {
    this.subscriptions.push(this.cityService.getAllByCountry(country).subscribe(cities => {
      this.departureCities = cities as City[];
    }))
  }

// todo: оптимизировать
  loadAllArrivalStationsByCity(city: any) {
    this.subscriptions.push(this.stationService.getAllByCity(city).subscribe(stations => {
      this.arrivalStation = stations as Station[];
    }))
  }

  loadAllArrivalCitiesByCountry(country: any) {
    this.subscriptions.push(this.cityService.getAllByCountry(country).subscribe(cities => {
      this.arrivalCities = cities as City[];
    }))
  }

  loadAllDepartureStationsByCity(city: any) {
    this.subscriptions.push(this.stationService.getAllByCity(city).subscribe(stations => {
      this.departureStation = stations as Station[];
    }))
  }

  addTrain(train: Train) {
    this.subscriptions.push(this.trainService.save(train).subscribe(train => {

    }))
  }

  loadRecordPage(page: number, size: number, direction: boolean, parameter: string) {

  }

  pageChanged(page: number, direction: boolean, parameter) {
    this.loadRecordPage(page, 7, direction, parameter);
  }
}
