package pl.edu.agh.to.kinofilmy.model.film;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepostory extends JpaRepository<Film, Long> {
}