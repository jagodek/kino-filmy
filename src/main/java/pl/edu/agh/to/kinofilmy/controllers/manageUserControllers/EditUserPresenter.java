package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesService;

import java.util.Objects;
import java.util.Optional;

@Controller
public class EditUserPresenter {
    @Autowired
    private KinoFilmyApplicationController applicationController;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RolesService rolesService;

    private Stage editUserStage;
    private Employee user;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private ChoiceBox<Roles> roleInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private Button submit;


    public void setStage(Stage stage) {
        this.editUserStage = stage;
    }
    public void setEmployee(Employee employee){
        this.user = employee;
        firstNameInput.setText(this.user.getFirstname());
        lastNameInput.setText(this.user.getLastname());
        roleInput.setValue(this.user.getRole());
        emailInput.setText(this.user.getEmail());
        phoneInput.setText(this.user.getPhoneNumber());
        usernameInput.setText(this.user.getUsername());
        passwordInput.setText(this.user.getPassword());
    }

    @FXML
    public void initialize(){
        for (Roles role :this.rolesService.findAll()) {
            this.roleInput.getItems().add(role);
        }
        this.roleInput.setOnAction((event -> {
            this.roleInput.setValue(this.roleInput.getValue());
        }));



    }

    @FXML
    public void handleSubmitAction(ActionEvent event){

            boolean isEdited = false;
            if(!Objects.equals(firstNameInput.getText(), this.user.getFirstname())){
                this.user.setFirstname(firstNameInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(lastNameInput.getText(), this.user.getLastname())){
                this.user.setLastname(lastNameInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(emailInput.getText(), this.user.getEmail())){
                this.user.setEmail(emailInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(phoneInput.getText(), this.user.getPhoneNumber())){
                this.user.setPhoneNumber(phoneInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(roleInput.getValue(), this.user.getRole())){
                this.user.setRole(roleInput.getValue());
                isEdited = true;
            }
            if(!Objects.equals(usernameInput.getText(), this.user.getUsername())){
                this.user.setUsername(usernameInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(passwordInput.getText(), this.user.getPassword())){
                this.user.setPassword(passwordInput.getText());
                isEdited = true;
            }

            if(isEdited){
                this.employeeService.addEmployee(this.user);
            }

            this.editUserStage.close();

    }
}
