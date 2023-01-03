package pl.edu.agh.to.kinofilmy.model.showing;

import pl.edu.agh.to.kinofilmy.model.film.Film;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


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
    private Float price;

    public Showing(Screen screen, Film film, Date date, Float price) {
        this.screen = screen;
        this.film = film;
        this.date = date;
        this.price = price;
    }

    public Showing(ShowingDisplay showingDisplay){
        this.setId(showingDisplay.getId());
        this.setDate(showingDisplay.getDate());
        this.setFilm(showingDisplay.getFilm());
        this.setScreen(showingDisplay.getScreen());
        this.setPrice(showingDisplay.getPrice());
    }

    public  Showing(){}
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

