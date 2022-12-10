package pl.edu.agh.to.kinofilmy.Seance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Seance {
    private Long id;
    private Long screenId;
    private Long filmId;
    private Date date;

    public Seance() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public Long getScreenId() {
        return screenId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public Date getDate() {
        return date;
    }
}

