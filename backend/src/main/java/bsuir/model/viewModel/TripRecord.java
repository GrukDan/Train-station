package bsuir.model.viewModel;

import bsuir.model.stationDetails.*;

public class TripRecord {
    private Country country;
    private City city;
    private Station station;
    private TrainModel trainModel;
    private Train train;
    private Trip trip;


    @Override
    public String toString() {
        return "TripRecord{" +
                "country=" + country +
                ", city=" + city +
                ", station=" + station +
                ", trainModel=" + trainModel +
                ", train=" + train +
                ", trip=" + trip +
                '}';
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public TrainModel getTrainModel() {
        return trainModel;
    }

    public void setTrainModel(TrainModel trainModel) {
        this.trainModel = trainModel;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
