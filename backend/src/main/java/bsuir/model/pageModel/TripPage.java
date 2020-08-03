package bsuir.model.pageModel;

import bsuir.model.viewModel.TripRecord;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TripPage extends Page {

    private List<TripRecord> tripRecords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TripPage tripPage = (TripPage) o;
        return Objects.equals(tripRecords, tripPage.tripRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tripRecords);
    }

    public TripPage(int page, int size, long totalElements, boolean direction, String parameter, List<TripRecord> tripRecords) {
        super(page, size, totalElements, direction, parameter);
        this.tripRecords = tripRecords;
    }
}
