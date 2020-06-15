package bsuir.model.taskDetails;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Comment {
    private long idComment;
    private String comment;
    private long place;
    private Timestamp timeOfCreation;
    private long owner;

    @Id
    @Column(name = "id_comment", nullable = false)
    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    @Basic
    @Column(name = "comment", nullable = false, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "place", nullable = false)
    public long getPlace() {
        return place;
    }

    public void setPlace(long place) {
        this.place = place;
    }

    @Basic
    @Column(name = "time_of_creation", nullable = false)
    public Timestamp getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Timestamp timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    @Basic
    @Column(name = "owner", nullable = false)
    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return idComment == comment1.idComment &&
                place == comment1.place &&
                owner == comment1.owner &&
                Objects.equals(comment, comment1.comment) &&
                Objects.equals(timeOfCreation, comment1.timeOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComment, comment, place, timeOfCreation, owner);
    }
}
