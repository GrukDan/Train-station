package bsuir.model.taskDetails;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Alternative {
    private long idAlternative;
    private long trip;
    private long task;

    @Id
    @Column(name = "id_alternative", nullable = false)
    public long getIdAlternative() {
        return idAlternative;
    }

    public void setIdAlternative(long idAlternative) {
        this.idAlternative = idAlternative;
    }

    @Basic
    @Column(name = "trip", nullable = false)
    public long getTrip() {
        return trip;
    }

    public void setTrip(long trip) {
        this.trip = trip;
    }

    @Basic
    @Column(name = "task", nullable = false)
    public long getTask() {
        return task;
    }

    public void setTask(long task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alternative that = (Alternative) o;
        return idAlternative == that.idAlternative &&
                trip == that.trip &&
                task == that.task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlternative, trip, task);
    }
}
