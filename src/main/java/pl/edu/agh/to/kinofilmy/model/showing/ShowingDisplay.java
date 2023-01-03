package pl.edu.agh.to.kinofilmy.model.showing;

import javafx.beans.property.*;
import pl.edu.agh.to.kinofilmy.model.film.Film;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;

import java.util.Date;

public class ShowingDisplay {
    private final LongProperty id;
    private final StringProperty screenName;
    private final StringProperty filmTitle;
    private final ObjectProperty<Date> date;
    private final ObjectProperty<Screen> screen;
    private final ObjectProperty<Film> film;

    public ShowingDisplay(Showing showing){
        this.id = new SimpleLongProperty(showing.getId());
        this.screenName = new SimpleStringProperty(showing.getScreen().getName());
        this.filmTitle = new SimpleStringProperty(showing.getFilm().getTitle());
        this.date = new SimpleObjectProperty<>(showing.getDate());
        this.screen = new SimpleObjectProperty<>(showing.getScreen());
        this.film = new SimpleObjectProperty<>(showing.getFilm());
    }

    public Long getId() {
        return id.get();
    }

    public Screen getScreen(){
        return this.screen.get();
    }

    public Film getFilm(){
        return this.film.get();
    }

    public String getScreenName() {
        return screenName.get();
    }

    public String getFilmTitle() {
        return filmTitle.get();
    }
    public Date getDate() {
        return date.get();
    }

    public LongProperty idProperty() {
        return id;
    }
    public ObjectProperty<Screen> screenProperty() {
        return screen;
    }

    public ObjectProperty<Film> filmProperty() {
        return film;
    }

    public StringProperty screenNameProperty() {
        return screenName;
    }

    public StringProperty filmTitleProperty() {
        return filmTitle;
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }
}
