package bsuir.model.taskDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "status", schema = "train_station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @Column(name = "id_status", nullable = false)
    private long idStatus;

    @Basic
    @Column(name = "status", nullable = false, length = 45)
    private String status;
}
