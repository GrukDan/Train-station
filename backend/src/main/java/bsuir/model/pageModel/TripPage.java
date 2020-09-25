package bsuir.model.pageModel;

import bsuir.model.viewModel.TripRecord;
import lombok.*;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TripPage extends Page {

    private List<TripRecord> tripRecords;

    @Builder
    public TripPage(int page, int size, long totalElements, boolean direction, String parameter, List<TripRecord> tripRecords) {
        super(page, size, totalElements, direction, parameter);
        this.tripRecords = tripRecords;
    }
}
