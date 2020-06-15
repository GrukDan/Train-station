package bsuir.model.stationDetails;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "train_model", schema = "train_station", catalog = "")
public class TrainModel {
    private long idTrainModel;
    private String model;

    @Id
    @Column(name = "id_train_model", nullable = false)
    public long getIdTrainModel() {
        return idTrainModel;
    }

    public void setIdTrainModel(long idTrainModel) {
        this.idTrainModel = idTrainModel;
    }

    @Basic
    @Column(name = "model", nullable = false, length = 45)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainModel that = (TrainModel) o;
        return idTrainModel == that.idTrainModel &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrainModel, model);
    }
}
