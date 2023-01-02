package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

import java.util.Optional;
import java.util.function.UnaryOperator;

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

    @FXML
    private Label loginLabel;




    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }


    @FXML
    public void initialize(){
        usernameText.textProperty().addListener((arg0,oldValue,newValue) -> {
            System.out.println("change");
            //when focus lost
                System.out.println("sth happening");
                if(usernameText.getText().isBlank()){
                    this.loginLabel.setVisible(true);
                }
                else{
                    this.loginLabel.setVisible(false);
                }

        });

//        UnaryOperator<TextFormatter.Change> loginRequired = (change -> {
//            if(change.getControlText().isBlank()){
//                this.loginLabel.setVisible(true);
//                return null;
//            }
//            else{
//                this.loginLabel.setVisible(false);
//                return change;
//            }
//        });
//        TextFormatter <String> tf = new TextFormatter<String>(loginRequired);
//        usernameText.setTextFormatter(tf);
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
