package bsuir.model.taskDetails;

import bsuir.model.userDetails.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "task", schema = "train_station")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @Column(name = "id_task", nullable = false)
    private long idTask;

    @Basic
    @Column(name = "task_creator", nullable = false)
    private long taskCreator;

    @Basic
    @Column(name = "date_of_creation", nullable = false)
    private Date dateOfCreation;

    @Basic
    @Column(name = "task_name", nullable = false, length = 45)
    private String taskName;

    @Basic
    @Column(name = "task_code", nullable = false, length = 45)
    private String taskCode;

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @Basic
    @Column(name = "status", nullable = false)
    private long status;

    @JsonIgnore
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

    public void setIdTask(long idTask) {
        this.idTask = idTask;
    }

    public void setTaskCreator(long taskCreator) {
        this.taskCreator = taskCreator;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    @JsonProperty
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public long getIdTask() {
        return idTask;
    }

    public long getTaskCreator() {
        return taskCreator;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public String getDescription() {
        return description;
    }

    public long getStatus() {
        return status;
    }

    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idTask == task.idTask &&
                taskCreator == task.taskCreator &&
                status == task.status &&
                dateOfCreation.equals(task.dateOfCreation) &&
                taskName.equals(task.taskName) &&
                taskCode.equals(task.taskCode) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask, taskCreator, dateOfCreation, taskName, taskCode, description, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", taskCreator=" + taskCreator +
                ", dateOfCreation=" + dateOfCreation +
                ", taskName='" + taskName + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", users=" + users.toString() +
                '}';
    }

    public List<Long> collectUserIds(){
        return users.stream()
                .map(User::getIdUser)
                .collect(Collectors.toList());
    }
}
