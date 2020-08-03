package bsuir.model.taskDetails;

import bsuir.model.userDetails.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "task", schema = "train_station")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @Column(name = "id_task", nullable = false)
    @EqualsAndHashCode.Include
    private long idTask;

    @Basic
    @Column(name = "task_creator", nullable = false)
    private long taskCreator;

    @Basic
    @Column(name = "date_of_creation", nullable = false)
    private Date dateOfCreation;

    @Basic
    @Column(name = "task_name", nullable = false, length = 45)
    @EqualsAndHashCode.Include
    private String taskName;

    @Basic
    @Column(name = "task_code", nullable = false, length = 45)
    @EqualsAndHashCode.Include
    private String taskCode;

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @Basic
    @Column(name = "status", nullable = false)
    private long status;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "tasks")
    private Set<User> users;

    public Task(@NonNull Task task){
        idTask = task.idTask;
        taskName = task.taskName;
        taskCode = task.taskCode;
        taskCreator = task.taskCreator;
        description = task.description;
        dateOfCreation = task.dateOfCreation;
        status = task.status;
        users = task.users;
    }
}
