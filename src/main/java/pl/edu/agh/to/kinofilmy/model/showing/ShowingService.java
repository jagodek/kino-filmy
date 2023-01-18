package pl.edu.agh.to.kinofilmy.model.showing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.ticket.TicketService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.*;

@Service
@Transactional
public class ShowingService {
    private final ShowingRepository repository;
    private  final TicketService ticketService;

    public ShowingService(ShowingRepository repository, TicketService ticketService) {
        this.repository = repository;
        this.ticketService = ticketService;
    }

    public Showing showingDisplayToShowing(ShowingDisplay showingDisplay){
        return new Showing(showingDisplay);
    }

    public ObservableList<ShowingDisplay> findAllShowingDisplay(){
        ObservableList<ShowingDisplay> showingDisplayList = FXCollections.observableArrayList();
        for(Showing showing: this.repository.findAll()){
            showingDisplayList.add(new ShowingDisplay(showing));
        }
        return showingDisplayList;
    }

    public void save(Showing showing){
        this.repository.save(showing);
    }

    public void update(Showing showing){
        Optional<Showing> optionalShowing = this.repository.findShowingById(showing.getId());
        if(optionalShowing.isPresent()){
            Showing old = optionalShowing.get();
            System.out.println(Objects.equals(old.getDate(), showing.getDate()));
            if(!Objects.equals(old.getScreen(), showing.getScreen())){
                old.setScreen(showing.getScreen());
            }
            if(!Objects.equals(old.getDate(), showing.getDate())){
                old.setDate(showing.getDate());
            }
            if(!Objects.equals(old.getFilm(), showing.getFilm())){
                old.setFilm(showing.getFilm());
            }
        }
    }

    public void delete(Showing showing){
        Optional<Showing> optionalShowing = this.repository.findShowingById(showing.getId());
        if(optionalShowing.isPresent()){
            this.repository.delete(optionalShowing.get());
        }
    }

    public List<Showing> getSuggested(){
        List<Showing> showings = this.repository.findShowingByDateAfter(Date.from(Instant.now()));
        Optional<Showing> trending = showings.stream()
                .map(showing -> new Pair<Showing, Integer>(showing, this.ticketService.countTicketsForShowing(showing)))
                .max((pair1, pair2) -> {
                    if (pair1.getValue() > pair2.getValue()) {
                        return 1;
                    }
                    if (pair1.getValue() < pair2.getValue()) {
                        return -1;
                    }
                    return 0;
                })
                .map(Pair::getKey);
        List<Showing> res = new LinkedList<>(this.repository.findShowingByFilmRecommendedEquals(true));
        if(trending.isPresent()){
            if(!res.contains(trending.get())){
                res.add(trending.get());
            }
        }
        return res;
    }
}
