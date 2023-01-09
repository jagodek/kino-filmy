package pl.edu.agh.to.kinofilmy.model.film;

import javafx.beans.property.LongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;
import java.util.*;


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

    public boolean exists(long id){
        if(this.repository.existsFilmById(id)){
            return true;
        }
        return false;
    }

    public Optional<Film> getFilmById(long id){
        return this.repository.findById(id);
    }


    public ObservableList<FilmStatisticTickets> getMoviesByTicketsSold(){
        Date date = new Date();
        ObservableList<FilmStatisticTickets> list = FXCollections.observableArrayList();
        for (Object[] pair:this.repository.findFilmsByTicketsSoldOnDate(date)) {
            Film film = (Film) pair[0];
            Long num = (Long) pair[1];
            list.add(new FilmStatisticTickets(film.getId(),film.getTitle(),num));
        }

        return list;
    }
}
