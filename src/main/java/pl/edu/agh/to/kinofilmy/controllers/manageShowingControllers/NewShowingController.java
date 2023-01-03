package pl.edu.agh.to.kinofilmy.controllers.manageShowingControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.Film;
import pl.edu.agh.to.kinofilmy.model.film.FilmDisplay;
import pl.edu.agh.to.kinofilmy.model.film.FilmService;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenDisplay;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenService;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;
import pl.edu.agh.to.kinofilmy.model.showing.ShowingService;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Controller
public class NewShowingController {
    private final ShowingService showingService;
    private final FilmService filmService;
    private final ScreenService screenService;
    private Stage newShowingStage;
    @FXML
    private ListView<FilmDisplay> filmList;
    @FXML
    private ListView<ScreenDisplay> screenList;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField timeInput;
    @FXML
    private Button submitButton;

    public NewShowingController(ShowingService showingService, FilmService filmService, ScreenService screenService) {
        this.showingService = showingService;
        this.filmService = filmService;
        this.screenService = screenService;
    }

    public void setNewShowingStage(Stage newShowingStage) {
        this.newShowingStage = newShowingStage;
    }

    @FXML
    public void initialize(){
        filmList.setItems(this.filmService.findAllAsFilmDisplay());
        screenList.setItems(this.screenService.findAllAsScreenDisplay());
        filmList.setCellFactory();
    }
    @FXML
    public void handleSubmitAction(ActionEvent event){
        Optional<Film> optionalFilm = filmService.getFilmById(filmList.getSelectionModel().getSelectedItem().getId());
        Optional<Screen> optionalScreen = screenService.getFilmById(screenList.getSelectionModel().getSelectedItem().getId());
        if(optionalFilm.isPresent() && optionalScreen.isPresent()){
            Showing showing = new Showing(
                    optionalScreen.get(),
                    optionalFilm.get(),
                    Date.from(
                            datePicker.getValue()
                            .atTime(LocalTime.parse(timeInput.getText()))
                            .atZone(ZoneId.systemDefault()).toInstant())
            );
            showingService.save(showing);
        }


    }
}
