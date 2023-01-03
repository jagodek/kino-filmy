package pl.edu.agh.to.kinofilmy.model.ticket;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenService;
import pl.edu.agh.to.kinofilmy.model.screen.Seat;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ScreenService screenService;

    public TicketService(TicketRepository ticketRepository, ScreenService screenService) {
        this.ticketRepository = ticketRepository;
        this.screenService = screenService;
    }

    public void save(Ticket ticket){
        this.ticketRepository.save(ticket);
    }
    public boolean clipTicket(Long id){
       Optional<Ticket> optionalTicket = this.ticketRepository.findById(id);
       if(optionalTicket.isPresent()){
           if(optionalTicket.get().getState().equals(TicketState.Clipped.getState())) return false;

           optionalTicket.get().setState(TicketState.Clipped.getState());
           return true;
       }
       else{
           return false;
       }
    }

    public List<Seat> getAvailableSeats(Showing showing){
        List<Ticket> ticketList = this.ticketRepository.findAllByShowing(showing);
        List<Seat> seats = this.screenService.getSeats(showing.getScreen());
        seats.removeIf(seat -> ticketList.stream().anyMatch(ticket ->
                ticket.getSeat() == seat.getSeatNumber() && ticket.getSeatRow() == seat.getRowNumber()));
        return seats;
    }

    public Seat getFirstAvailableSeat(Showing showing){
        return this.getAvailableSeats(showing).get(0);
    }
    public int countTicketsForShowing(Showing showing){
        return  this.ticketRepository.countByShowing(showing);
    }
}
