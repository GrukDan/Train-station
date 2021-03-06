package bsuir.model.viewModel;

import bsuir.model.taskDetails.Task;
import lombok.*;


//todo: заменть на агрегацию
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class TaskViewModel extends Task {
    private String taskCreatorName;
    private String taskCreatorSurname;
    private String statusName;

    public TaskViewModel(Task task){
        super(task);
    }
}
