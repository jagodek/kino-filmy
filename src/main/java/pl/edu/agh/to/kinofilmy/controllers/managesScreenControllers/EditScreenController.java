package pl.edu.agh.to.kinofilmy.controllers.managesScreenControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenService;

public class EditScreenController {
    private ScreenService screenService;

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
        this.screenService.update(this.screen);
        this.editUserStage.close();

    }


}
