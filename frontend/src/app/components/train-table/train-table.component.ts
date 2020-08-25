import {Component, OnDestroy, OnInit, TemplateRef, HostListener} from '@angular/core';
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
import {ModalDirective} from "ngx-bootstrap/modal";


@Component({
  selector: 'app-train-table',
  templateUrl: './train-table.component.html',
  styleUrls: ['./train-table.component.css'],
  providers: [{provide: BsDropdownConfig, useValue: {isAnimated: true, autoClose: true}}]
})
export class TrainTableComponent implements OnInit, OnDestroy {

  countryForm: FormGroup;
  cityForm: FormGroup;
  stationForm: FormGroup;
  trainModelForm: FormGroup;
  trainForm: FormGroup;
  tripForm: FormGroup;

  country: Country;
  city: City;
  station: Station;
  trainModel: TrainModel;
  train: Train;
  trip: Trip;

  maxDate: Date;
  minDate: Date;

  idDepartureCity: number;
  idArrivalCity: number;

  countries: Country[] = [];
  cities: City[] = [];
  trainModels: TrainModel[] = [];
  stations: Station[];
  trains: Train[] = [];

  arrivalCities: City[] = [];
  departureCities: City[] = [];
  arrivalStation: Station[] = [];
  departureStation: Station[] = [];

  tripRecords: TripRecord[] = [];
  subscriptions: Subscription[] = [];

  editableTripRecord:TripRecord;

  size: number = 7;
  totalElements: number = 0;
  direction: boolean = false;
  parameter: string;
  parameters: string[] = [];

  constructor(public modalWindowService: ModalWindowService,
              private validationService: ValidationService,
              private localeService: BsLocaleService,
              private countryService: CountryService,
              private cityService: CityService,
              private stationService: StationService,
              private trainModelService: TrainModelService,
              private trainService: TrainService,
              private tripService: TripService) {
  }

  ngOnInit() {

    this.country = new Country();
    this.city = new City();
    this.station = new Station();
    this.trainModel = new TrainModel();
    this.train = new Train();
    this.trip = new Trip();

    this.minDate = new Date("01.01.1965");
    this.maxDate = new Date();

    this.loadAllStations();
    this.loadAllCountries();
    this.loadAllCities();
    this.loadAllTrainModels();
    this.loadAllTrains();

    this.loadRecordPage(0, this.size, this.direction, this.parameters[0]);
  }

  @HostListener('window:beforeunload')
  ngOnDestroy(): void {
    this.dispose();
  }

  dispose() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  changeDirection() {
    this.direction = !this.direction;
  }

  sort(direction: boolean, parameter: string) {
    this.changeDirection();
    this.loadRecordPage(0, this.size, this.direction, parameter);
  }

  addCountry(country: Country) {
    this.subscriptions.push(this.countryService.save(country).subscribe(() => {
      this.modalWindowService.closeModal();
    }))
  }

  addCity(city: City, country: string) {
    city.country = parseInt(country);
    this.subscriptions.push(this.cityService.save(city).subscribe(() => {
      this.modalWindowService.closeModal();
    }))
  }

  addStation(station: Station, city: string) {
    station.city = parseInt(city);
    this.subscriptions.push(this.stationService.save(station).subscribe(() => {
      this.modalWindowService.closeModal();
    }))
  }

  addTrainModel(trainModel: TrainModel) {
    this.subscriptions.push(this.trainModelService.save(trainModel).subscribe(() => {
      this.modalWindowService.closeModal();
    }))
  }

  loadAllTrainModels() {
    this.subscriptions.push(this.trainModelService.getAll().subscribe(trainModels => {
      this.trainModels = trainModels as TrainModel[];
    }))
  }

  loadAllCountries() {
    this.subscriptions.push(this.countryService.getAll().subscribe(countries => {
      this.countries = countries as Country[];
    }))
  }

  loadAllCities() {
    this.subscriptions.push(this.cityService.getAll().subscribe(cities => {
      this.cities = cities as City[];
    }))
  }

  loadAllStations() {
    this.subscriptions.push(this.stationService.getAll().subscribe(stations => {
      this.stations = stations as Station[];
    }))
  }

  loadAllTrains() {
    this.subscriptions.push(this.trainService.getAll().subscribe(trains => {
      this.trains = trains as Train[];
    }))
  }

  loadAllCitiesByCountry(country: any) {
    this.subscriptions.push(this.cityService.getAllByCountry(country).subscribe(cities => {
      this.cities = cities as City[];
    }))
  }

  openTripModal(newTrip: TemplateRef<any>) {
    this.tripForm == null ?
      this.tripForm = this.validationService.getTripFormGroup() : this.tripForm;
    this.modalWindowService.openModal(newTrip);
    this.loadAllCountries();
    this.loadAllTrainModels();
  }

  openTrainModal(newTrain: TemplateRef<any>) {
    this.trainForm == null ?
      this.trainForm = this.validationService.getTrainFormGroup() : this.trainForm;
    this.modalWindowService.openModal(newTrain);
    this.loadAllTrainModels();
  }

  openTrainModelModal(newTrainModel: TemplateRef<any>) {
    this.trainModelForm == null ?
      this.trainModelForm = this.validationService.getTrainModelFormGroup() : this.trainModelForm;
    this.modalWindowService.openModal(newTrainModel);
  }

  openStationModal(newStation: TemplateRef<any>) {
    this.loadAllCountries();
    this.stationForm == null ?
      this.stationForm = this.validationService.getStationFormGroup() : this.stationForm;
    this.modalWindowService.openModal(newStation);
  }

  openCityModal(newCity: TemplateRef<any>) {
    this.cityForm == null ?
      this.cityForm = this.validationService.getCityFormGroup() : this.cityForm;
    this.modalWindowService.openModal(newCity);
    this.loadAllCountries();
  }

  openCountryModal(newCountry: TemplateRef<any>) {
    this.countryForm == null ?
      this.countryForm = this.validationService.getCountryFormGroup() : this.countryForm;
    this.modalWindowService.openModal(newCountry);
  }


  addTrip(trip: Trip) {
    this.subscriptions.push(this.tripService.save(trip).subscribe(
      () => this.modalWindowService.closeModal()
    ));
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
    this.subscriptions.push(this.trainService.save(train).subscribe(() => {
      this.modalWindowService.closeModal();
    }));
  }

  loadRecordPage(page: number, size: number, direction: boolean, parameter: string) {
    this.subscriptions.push(this.tripService.getPage(page, size, direction, parameter)
      .subscribe(tripPage => {
        this.tripRecords = tripPage.tripRecords as TripRecord[];
        this.totalElements = tripPage.totalElements / this.size * 10;
      }))
  }

  pageChanged(page: number, direction: boolean, parameter) {
    this.loadRecordPage(page, 7, direction, parameter);
  }

  loadAllTrainsByTrainModel(trainModel: any) {
    this.subscriptions.push(this.trainService.getAllByModel(trainModel).subscribe(trains => {
      this.trains = trains as Train[];
    }))
  }

  get _trainModel() {
    return this.trainModelForm.get('trainModel')
  }

  get _trainModelForTrain() {
    return this.trainForm.get('trainModel')
  }

  get _dateOfCreation() {
    return this.trainForm.get('dateOfCreation')
  }

  get _train() {
    return this.tripForm.get('train')
  }

  get _departureCountry() {
    return this.tripForm.get('departureCountry');
  }

  get _arrivalCountry() {
    return this.tripForm.get('arrivalCountry');
  }

  get _arrivalCity() {
    return this.tripForm.get('arrivalCity');
  }

  get _departureCity() {
    return this.tripForm.get('departureCity');
  }

  get _departureStation() {
    return this.tripForm.get('departureStation')
  }

  get _arrivalStation() {
    return this.tripForm.get('arrivalStation')
  }

  get _trainModelForTrip() {
    return this.tripForm.get('trainModel');
  }

  get _country() {
    return this.countryForm.get('country')
  }

  get _countryForCity() {
    return this.cityForm.get('country')
  }

  get _city() {
    return this.cityForm.get('city')
  }

  get _cityForStation() {
    return this.stationForm.get('city')
  }

  get _station() {
    return this.stationForm.get('station')
  }

  buildStationChild(city: number): any[] {
    return this.stations
      .filter(station => station.city == city)
      .map(station => {
        return {
          name: station.station,
          hasChildren: false,
          isExpanded: false
        }
      })
  }

  buildCitiesChild(country: number): any[] {
    return this.cities
      .filter(city => city.country == country)
      .map(city => {
        return {
          id: city.idCity,
          name: city.city,
          hasChildren: false,
          isExpanded: false,
          children: this.buildStationChild(city.idCity),
        }
      });
  }

  buildCountriesRoot(): any[] {
    return this.countries
      .map(country => {
        return {
          id: country.idCountry,
          name: country.country,
          hasChildren: true,
          children: this.buildCitiesChild(country.idCountry),
        }
      })
  }

  buildTrainChild(trainModel: number): any[] {
    return this.trains
      .filter(train => train.model == trainModel)
      .map(train => {
        return {
          name: '№' + train.idTrain + ' of ' + train.dateOfCreation,
          hasChildren: false,
          isExpanded: false
        }
      });
  }

  buildTrainModelsRoot(): any[] {
    return this.trainModels.map(trainModel => {
      return {
        id: trainModel.idTrainModel,
        name: trainModel.model,
        hasChildren: true,
        children: this.buildTrainChild(trainModel.idTrainModel),
      }
    })
  }

  clickCityForStation(value: string) {
    this.station.city = Number(value);
  }

  openEditTripModal(editTripModal: TemplateRef<any>,tripRecord:TripRecord) {
    this.modalWindowService.openModal(editTripModal);
    this.modalWindowService.largeModal();
    this.editableTripRecord = tripRecord;
  }
}
