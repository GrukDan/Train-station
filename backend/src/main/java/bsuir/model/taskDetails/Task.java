package bsuir.model.taskDetails;

import bsuir.model.userDetails.User;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Task {
    private long idTask;
    private long taskCreator;
    private Date dateOfCreation;
    private String taskName;
    private String taskCode;
    private String description;
    private long status;
    Set<User> users;

    @Id
    @Column(name = "id_task", nullable = false)
    public long getIdTask() {
        return idTask;
    }

    public void setIdTask(long idTask) {
        this.idTask = idTask;
    }

    @Basic
    @Column(name = "task_creator", nullable = false)
    public long getTaskCreator() {
        return taskCreator;
    }

    public void setTaskCreator(long taskCreator) {
        this.taskCreator = taskCreator;
    }

    @Basic
    @Column(name = "date_of_creation", nullable = false)
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Basic
    @Column(name = "task_name", nullable = false, length = 45)
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "task_code", nullable = false, length = 45)
    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idTask == task.idTask &&
                taskCreator == task.taskCreator &&
                status == task.status &&
                Objects.equals(dateOfCreation, task.dateOfCreation) &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(taskCode, task.taskCode) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask, taskCreator, dateOfCreation, taskName, taskCode, description, status);
    }

    @ManyToMany(mappedBy = "tasks")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
