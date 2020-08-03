package bsuir.model.userDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message", schema = "train_station")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @Column(name = "id_message", nullable = false)
    @EqualsAndHashCode.Include
    private long idMessage;

    @Basic
    @Column(name = "sender", nullable = false)
    private long sender;

    @Basic
    @Column(name = "recipient", nullable = false)
    private long recipient;

    @Basic
    @Column(name = "message", nullable = false, length = -1)
    private String message;

    @Basic
    @Column(name = "time_of_creation", nullable = false)
    @EqualsAndHashCode.Include
    private Timestamp timeOfCreation;
}
