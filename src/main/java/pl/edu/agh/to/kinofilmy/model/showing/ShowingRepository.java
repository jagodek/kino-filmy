package pl.edu.agh.to.kinofilmy.model.showing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShowingRepository extends JpaRepository<Showing, Long> {
    Optional<Showing> findShowingById(Long id);
    List<Showing> findShowingByDateAfter(Date date);
    List<Showing> findShowingByFilmRecommendedEquals(Boolean isRecommended);
}
