package bsuir.model.pageModel;

import bsuir.model.viewModel.TripRecord;
import lombok.*;

import java.util.List;
import java.util.Objects;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripPage extends Page {

    private List<TripRecord> tripRecords;

    public TripPage(int page, int size, long totalElements, boolean direction, String parameter, List<TripRecord> tripRecords) {
        super(page, size, totalElements, direction, parameter);
        this.tripRecords = tripRecords;
    }
}
