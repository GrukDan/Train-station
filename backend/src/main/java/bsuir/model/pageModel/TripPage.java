package bsuir.model.pageModel;

import bsuir.model.viewModel.TripRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TripPage extends Page {

    private List<TripRecord> tripRecords;

    public TripPage(int page, int size, long totalElements, boolean direction, String parameter, List<TripRecord> tripRecords) {
        super(page, size, totalElements, direction, parameter);
        this.tripRecords = tripRecords;
    }
}
