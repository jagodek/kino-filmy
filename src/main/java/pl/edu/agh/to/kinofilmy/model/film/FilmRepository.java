package pl.edu.agh.to.kinofilmy.model.film;

import javafx.util.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    public boolean existsFilmById(long id);

    @Query("SELECT m, COUNT(t) FROM Film m INNER JOIN Showing s ON s.film = m inner join Ticket t on t.showing = s WHERE t.saleDate = :date GROUP BY m.id ORDER BY COUNT(t.id) DESC")
    List<Object[]> findFilmsByTicketsSoldOnDate(@Param("date") Date date);
}
