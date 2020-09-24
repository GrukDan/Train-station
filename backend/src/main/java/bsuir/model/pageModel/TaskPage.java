package bsuir.model.pageModel;

import bsuir.model.viewModel.TaskViewModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;


//todo: заменть конструктор на builder
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class TaskPage extends Page {

    List<TaskViewModel> taskViewModels;

    public TaskPage(int page, int size, long totalElements, boolean direction, String parameter, List<TaskViewModel> taskViewModels) {
        super(page, size, totalElements, direction, parameter);
        this.taskViewModels = taskViewModels;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TaskPage taskPage = (TaskPage) o;
        return Objects.equals(taskViewModels, taskPage.taskViewModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taskViewModels);
    }
}
