package pl.edu.agh.to.kinofilmy.model.screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Iterable<Screen> getScreens(){
        return repository.findAll();
    }
    public void addScreen(Screen screen){
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
        this.repository.delete(screen);
    }
}
