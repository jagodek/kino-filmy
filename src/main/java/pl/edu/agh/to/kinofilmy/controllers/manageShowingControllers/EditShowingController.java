package pl.edu.agh.to.kinofilmy.controllers.manageShowingControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.FilmDisplay;
import pl.edu.agh.to.kinofilmy.model.film.FilmService;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenDisplay;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenService;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;
import pl.edu.agh.to.kinofilmy.model.showing.ShowingService;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class EditShowingController {
    private final ShowingService showingService;
    private final FilmService filmService;
    private final ScreenService screenService;
    private Stage editShowingStage;

    private Showing showing;
    @FXML
    private TableView<FilmDisplay> filmList;
    @FXML
    private TableColumn<FilmDisplay, String> filmTitleColumn;
    @FXML
    private TableView<ScreenDisplay> screenList;
    @FXML
    private TableColumn<ScreenDisplay, String> screenNameColumn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField timeInput;
    @FXML
    private Button submitButton;

    public EditShowingController(ShowingService showingService, FilmService filmService, ScreenService screenService) {
        this.showingService = showingService;
        this.filmService = filmService;
        this.screenService = screenService;
    }

    public void setEditShowingStage(Stage editShowingStage) {
        this.editShowingStage = editShowingStage;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    @FXML
    public void initialize(){

    }

    @FXML
    public void handleSubmitAction(ActionEvent event) {
        this.showing.setFilm(filmService.filmDisplayToFilm(filmList.getSelectionModel().getSelectedItem()));
        this.showing.setScreen(screenService.screenDisplayToScreen(screenList.getSelectionModel().getSelectedItem()));
        this.showing.setDate( Date.from(
                datePicker.getValue()
                        .atTime(LocalTime.parse(timeInput.getText()))
                        .atZone(ZoneId.systemDefault()).toInstant()));
        this.showingService.update(showing);
    }
}
