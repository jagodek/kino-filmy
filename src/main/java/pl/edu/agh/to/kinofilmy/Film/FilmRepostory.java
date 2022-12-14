package pl.edu.agh.to.kinofilmy.Film;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepostory extends JpaRepository<Film, Long> {
}
