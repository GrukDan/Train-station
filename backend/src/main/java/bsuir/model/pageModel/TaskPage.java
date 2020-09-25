package bsuir.model.pageModel;

import bsuir.model.viewModel.TaskViewModel;
import lombok.*;

import java.util.List;
import java.util.Objects;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TaskPage extends Page {

    private List<TaskViewModel> taskViewModels;

    @Builder
    public TaskPage(int page, int size, long totalElements, boolean direction, String parameter, List<TaskViewModel> taskViewModels) {
        super(page, size, totalElements, direction, parameter);
        this.taskViewModels = taskViewModels;
    }
}
