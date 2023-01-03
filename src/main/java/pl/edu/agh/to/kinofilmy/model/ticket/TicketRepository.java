package pl.edu.agh.to.kinofilmy.model.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByShowing(Showing showing);
}
