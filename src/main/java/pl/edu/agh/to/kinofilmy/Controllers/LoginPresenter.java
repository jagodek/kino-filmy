package pl.edu.agh.to.kinofilmy.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPresenter {

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
}
