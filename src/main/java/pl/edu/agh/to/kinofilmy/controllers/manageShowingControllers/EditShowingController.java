package pl.edu.agh.to.kinofilmy.controllers.manageShowingControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.Film;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;
import pl.edu.agh.to.kinofilmy.model.showing.ShowingService;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class EditShowingController {
    private final ShowingService showingService;
    private Stage editShowingStage;

    private Showing showing;

    @FXML
    private ListView<Film> filmList;
    @FXML
    private ListView<Screen> screenList;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField timeInput;
    @FXML
    private Button submitButton;

    public EditShowingController(ShowingService showingService) {
        this.showingService = showingService;
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
        this.showing.setFilm(filmList.getSelectionModel().getSelectedItem());
        this.showing.setScreen(screenList.getSelectionModel().getSelectedItem());
        this.showing.setDate( Date.from(
                datePicker.getValue()
                        .atTime(LocalTime.parse(timeInput.getText()))
                        .atZone(ZoneId.systemDefault()).toInstant()));
    }
}
