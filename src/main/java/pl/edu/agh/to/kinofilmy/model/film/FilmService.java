package pl.edu.agh.to.kinofilmy.model.film;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class FilmService {

    private final FilmRepository repository;

    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }


    public List<Film> findAll(){
        return this.repository.findAll();
    }

    public ObservableList<FilmDisplay> findAllAsFilmDisplay(){
        ObservableList<FilmDisplay> filmDisplayList = FXCollections.observableArrayList();
        for(Film f: this.repository.findAll()){
            filmDisplayList.add(new FilmDisplay(f));
        }
        return filmDisplayList;
    }

    public void save(Film film) {
        this.repository.save(film);
    }

    public void delete(Film film){
        this.repository.delete(film);
    }

    public Film filmDisplayToFilm(FilmDisplay filmDisplay){
        try{
            Film film = new Film(filmDisplay);
            film.setIcon(repository.getReferenceById(filmDisplay.getId()).getIcon());
            return film;
        } catch (EntityNotFoundException e) {
            return new Film(filmDisplay);
        }
    }

    public Image getFilmImageById(long id){
        Film film = repository.getReferenceById(id);
        byte[] byteImg = film.getIcon();
        return new Image(new ByteArrayInputStream(byteImg));
    }

    public Optional<Film> getFilmById(long id){
        return this.repository.findById(id);
    }
}
