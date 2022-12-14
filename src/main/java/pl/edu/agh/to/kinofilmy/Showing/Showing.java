package pl.edu.agh.to.kinofilmy.Showing;

import pl.edu.agh.to.kinofilmy.Film.Film;
import pl.edu.agh.to.kinofilmy.Screen.Screen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;


/**
 * Showing is the class that represents a showing that user can attend,
 * It stores infromation about which movie will be displayed, on witch screen and when.
 */
@Entity
public class Showing {
    private Long id;
    private Screen screen;
    private Film film;
    private Date date;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    @ManyToOne
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

