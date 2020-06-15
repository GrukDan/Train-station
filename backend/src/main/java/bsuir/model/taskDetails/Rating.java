package bsuir.model.taskDetails;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Rating {
    private long idRating;
    private long alternative1;
    private long alternative2;
    private long expert;
    private int rating;

    @Id
    @Column(name = "id_rating", nullable = false)
    public long getIdRating() {
        return idRating;
    }

    public void setIdRating(long idRating) {
        this.idRating = idRating;
    }

    @Basic
    @Column(name = "alternative1", nullable = false)
    public long getAlternative1() {
        return alternative1;
    }

    public void setAlternative1(long alternative1) {
        this.alternative1 = alternative1;
    }

    @Basic
    @Column(name = "alternative2", nullable = false)
    public long getAlternative2() {
        return alternative2;
    }

    public void setAlternative2(long alternative2) {
        this.alternative2 = alternative2;
    }

    @Basic
    @Column(name = "expert", nullable = false)
    public long getExpert() {
        return expert;
    }

    public void setExpert(long expert) {
        this.expert = expert;
    }

    @Basic
    @Column(name = "rating", nullable = false)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return idRating == rating1.idRating &&
                alternative1 == rating1.alternative1 &&
                alternative2 == rating1.alternative2 &&
                expert == rating1.expert &&
                rating == rating1.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRating, alternative1, alternative2, expert, rating);
    }
}
