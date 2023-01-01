package pl.edu.agh.to.kinofilmy.model.film;

import org.springframework.beans.factory.annotation.Autowired;


public class FilmService {

    @Autowired
    private FilmRepository repository;


    public Iterable<Film> findAll(){
        return this.repository.findAll();
    }

    public void save(Film film) {
        this.repository.save(film);
    }
}
