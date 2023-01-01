package pl.edu.agh.to.kinofilmy.model.film;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
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

    public Film filmDisplayToFilm(FilmDisplay filmDisplay){
        return new Film();
    }

}
