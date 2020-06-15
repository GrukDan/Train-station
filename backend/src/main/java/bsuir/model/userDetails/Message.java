package bsuir.model.userDetails;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Message {
    private long idMessage;
    private long sender;
    private long recipient;
    private String message;
    private Timestamp timeOfCreation;

    @Id
    @Column(name = "id_message", nullable = false)
    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    @Basic
    @Column(name = "sender", nullable = false)
    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "recipient", nullable = false)
    public long getRecipient() {
        return recipient;
    }

    public void setRecipient(long recipient) {
        this.recipient = recipient;
    }

    @Basic
    @Column(name = "message", nullable = false, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "time_of_creation", nullable = false)
    public Timestamp getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Timestamp timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return idMessage == message1.idMessage &&
                sender == message1.sender &&
                recipient == message1.recipient &&
                Objects.equals(message, message1.message) &&
                Objects.equals(timeOfCreation, message1.timeOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMessage, sender, recipient, message, timeOfCreation);
    }
}
