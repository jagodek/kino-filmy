package pl.edu.agh.to.kinofilmy.model.showing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenDisplay;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ShowingService {
    private final ShowingRepository repository;

    public ShowingService(ShowingRepository repository) {
        this.repository = repository;
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
}
