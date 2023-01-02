package pl.edu.agh.to.kinofilmy.model.film;

import javafx.beans.property.*;

import java.time.LocalTime;

public class FilmDisplay {

    private LongProperty id;
    private StringProperty title;
    private ObjectProperty<LocalTime> runtime;
    private StringProperty genre;
    private StringProperty director;


    public FilmDisplay(Film film){
        this.id = new SimpleLongProperty(film.getId());
        this.title = new SimpleStringProperty(film.getTitle());
        this.runtime = new SimpleObjectProperty<>(film.getRuntime());
        this.genre = new SimpleStringProperty(film.getGenre());
        this.director = new SimpleStringProperty(film.getDirector());
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public LocalTime getRuntime() {
        return runtime.get();
    }

    public ObjectProperty<LocalTime> runtimeProperty() {
        return runtime;
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public String getDirector() {
        return director.get();
    }

    public StringProperty directorProperty() {
        return director;
    }
}
