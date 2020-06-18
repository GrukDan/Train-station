package bsuir.model.pageModel;

import bsuir.model.viewModel.TripRecord;

import java.util.List;

public class TripPage extends Page {
    private List<TripRecord> tripRecords;

    public List<TripRecord> getTripRecords() {
        return tripRecords;
    }

    public void setTripRecords(List<TripRecord> tripRecords) {
        this.tripRecords = tripRecords;
    }
}
