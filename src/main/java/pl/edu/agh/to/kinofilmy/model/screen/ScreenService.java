package pl.edu.agh.to.kinofilmy.model.screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.film.Film;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ScreenService {
    private final ScreenRepository repository;

    public ScreenService(ScreenRepository repository) {
        this.repository = repository;
    }

    public Screen screenDisplayToScreen(ScreenDisplay screenDisplay){
        return new Screen(screenDisplay);
    }

    public ObservableList<ScreenDisplay> findAllAsScreenDisplay(){
        ObservableList<ScreenDisplay> screenDisplayList = FXCollections.observableArrayList();
        for(Screen screen: this.repository.findAll()){
            screenDisplayList.add(new ScreenDisplay(screen));
        }
        return screenDisplayList;
    }
    public void save(Screen screen){
        this.repository.save(screen);
    }
    public void update(Screen screen){
        Optional<Screen> optionalScreen = this.repository.findScreenById(screen.getId());
        if(optionalScreen.isPresent()){
            Screen old = optionalScreen.get();

            if(!Objects.equals(old.getName(), screen.getName())){
                old.setName(screen.getName());
            }
            if(!Objects.equals(old.getRowNumber(), screen.getRowNumber())){
                old.setRowNumber(screen.getRowNumber());
            }
            if(!Objects.equals(old.getSeatsNumber(), screen.getSeatsNumber())){
                old.setSeatsNumber(screen.getSeatsNumber());
            }
        }
    }
    public void deleteScreen(Screen screen){
        Optional<Screen> optionalScreen = this.repository.findScreenById(screen.getId());
        if (optionalScreen.isPresent()){
            this.repository.delete(optionalScreen.get());
        }
    }

    public Optional<Screen> getFilmById(long id){
        return this.repository.findScreenById(id);
    }

    public List<Seat> getSeats(Screen screen){
        List<Seat> seats = new LinkedList<>();
        for (int i = 1, j = 1, k = 1; i <= screen.getSeatsNumber(); i++, k++) {
            seats.add(new Seat(screen, k, j));
            if(i == screen.getSeatsNumber()/screen.getRowNumber()) {
                j++;
                k = 1;
            }
        }
        return seats;
    }
}
