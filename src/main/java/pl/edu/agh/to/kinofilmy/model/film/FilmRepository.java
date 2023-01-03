package pl.edu.agh.to.kinofilmy.model.film;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
    public boolean existsFilmById(long id);
}
