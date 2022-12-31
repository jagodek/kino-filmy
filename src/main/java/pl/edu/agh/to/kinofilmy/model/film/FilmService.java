package pl.edu.agh.to.kinofilmy.model.film;

import org.springframework.stereotype.Service;

@Service
public class FilmService {

    final FilmRepostory filmRepostory;

    public FilmService(FilmRepostory filmRepostory) {
        this.filmRepostory = filmRepostory;
    }


}
