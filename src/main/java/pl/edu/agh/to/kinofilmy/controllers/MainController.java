package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;


@Controller
public class MainController {

    private final KinoFilmyApplicationController applicationController;

    private Stage mainStage;


    public MainController(KinoFilmyApplicationController applicationController) {
        this.applicationController = applicationController;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private Button newUserButton;

    @FXML
    public void handleNewUserAction(ActionEvent event){
        applicationController.showNewUserForm(mainStage);
    }
}
