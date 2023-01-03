package pl.edu.agh.to.kinofilmy.controllers.managesScreenControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenService;

@Controller
public class EditScreenController {
    private final ScreenService screenService;

    private Stage editUserStage;
    private Screen screen;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField seatsNumberInput;

    @FXML
    private TextField rowNumberInput;

    @FXML
    private Button submit;

    public EditScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    public void setStage(Stage stage) {
        this.editUserStage = stage;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
        nameInput.setText(this.screen.getName());
        seatsNumberInput.setText(String.valueOf(this.screen.getSeatsNumber()));
        rowNumberInput.setText(String.valueOf(this.screen.getRowNumber()));
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void handleSubmitAction(ActionEvent event) {
        this.screen.setName(nameInput.getText());
        this.screen.setSeatsNumber(Integer.parseInt(seatsNumberInput.getText()));
        this.screen.setRowNumber(Integer.parseInt(rowNumberInput.getText()));
        this.screenService.update(this.screen);
        this.editUserStage.close();

    }


}
