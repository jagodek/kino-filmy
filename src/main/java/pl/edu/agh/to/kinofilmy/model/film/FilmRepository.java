package pl.edu.agh.to.kinofilmy.model.film;

import javafx.util.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    public boolean existsFilmById(long id);

    @Query("SELECT m, COUNT(t.id) FROM Film m INNER JOIN Showing s ON s.film = m inner join Ticket t on t.showing = s WHERE YEAR (t.saleDate)= year(:date) and month (t.saleDate)= month(:date) and day(t.saleDate) = day(:date) GROUP BY m.id ORDER BY COUNT(t.id) DESC")
    Page<Object[]> findFilmsByTicketsSoldOnDate(@Param("date") Date date, Pageable pageable);

    @Query("SELECT m, COUNT(t.id) FROM Film m INNER JOIN Showing s ON s.film = m inner join Ticket t on t.showing = s WHERE YEAR (t.saleDate)= year(:date) and month (t.saleDate)= month(:date)  GROUP BY m.id ORDER BY COUNT(t.id) DESC")
    Page<Object[]> findFilmsByTicketsSoldOnMonth(@Param("date") Date date, Pageable pageable);

    @Query("SELECT m, COUNT(t.id) FROM Film m INNER JOIN Showing s ON s.film = m inner join Ticket t on t.showing = s WHERE YEAR (t.saleDate)= year(:date)  GROUP BY m.id ORDER BY COUNT(t.id) DESC")
    Page<Object[]> findFilmsByTicketsSoldOnYear(@Param("date") Date date, Pageable pageable);


}
