package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

import java.util.Optional;

@Controller
public class LoginPresenter {

    @Autowired
    private KinoFilmyApplicationController applicationController;

    private Stage loginStage;

    @Autowired
    EmployeeService employeeService;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button loginButton;


    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }


    @FXML
    public void handleOkAction(ActionEvent event){
        Optional<Roles> rolesOptional = employeeService.login(usernameText.getText(), passwordText.getText());
        if(rolesOptional.isEmpty()){
            applicationController.displayMessage(loginStage, "Incorrect username or password.");
        } else {
            applicationController.setUserRole(rolesOptional.get());
            loginStage.close();
        }
    }
}
