package pl.edu.agh.to.kinofilmy.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPresenter {

    private KinoFilmyApplicationController applicationController;

    private Stage loginStage;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;

    @FXML
    private Button loginButton;


    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    public void setApplicationController(KinoFilmyApplicationController applicationController) {
        this.applicationController = applicationController;
    }

    @FXML
    public void handleOkAction(ActionEvent event){
        applicationController.displayMessage(loginStage, "OK!");
        loginStage.close();
    }
}
