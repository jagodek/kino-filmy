package pl.edu.agh.to.kinofilmy.model.screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

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
        int seatsInRow = screen.getSeatsNumber() % screen.getRowNumber() == 0 ?
                screen.getSeatsNumber()/screen.getRowNumber():
                screen.getSeatsNumber()/screen.getRowNumber() + 1,
            biggerRows = screen.getSeatsNumber() % screen.getRowNumber() == 0 ? 0 :
                    screen.getSeatsNumber() % screen.getRowNumber();
        for (int seat = 1, seatInRow = 1, row = 1; seat <= screen.getSeatsNumber(); seat++, seatInRow++) {
            seats.add(new Seat(screen, row, seatInRow));
            if(seatInRow == seatsInRow) {
                row++;
                seatInRow = 0;
                biggerRows--;
                if(biggerRows == 0){
                    seatsInRow--;
                    biggerRows = -1;
                }
            }
        }
        return seats;
    }
}
