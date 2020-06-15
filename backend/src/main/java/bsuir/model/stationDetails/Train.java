package bsuir.model.stationDetails;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Train {
    private long idTrain;
    private long model;
    private Date dateOfCreation;

    @Id
    @Column(name = "id_train", nullable = false)
    public long getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(long idTrain) {
        this.idTrain = idTrain;
    }

    @Basic
    @Column(name = "model", nullable = false)
    public long getModel() {
        return model;
    }

    public void setModel(long model) {
        this.model = model;
    }

    @Basic
    @Column(name = "date_of_creation", nullable = false)
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return idTrain == train.idTrain &&
                model == train.model &&
                Objects.equals(dateOfCreation, train.dateOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrain, model, dateOfCreation);
    }
}
