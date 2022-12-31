package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

@Controller
public class NewUserController {

    private final KinoFilmyApplicationController applicationController;

    private final EmployeeService employeeService;

    private Stage newUserStage;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField roleInput;

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

    public NewUserController(KinoFilmyApplicationController applicationController, EmployeeService employeeService){
        this.applicationController = applicationController;
        this.employeeService = employeeService;
    }

    public void setNewUserStage(Stage newUserStage) {
        this.newUserStage = newUserStage;
    }

    @FXML
    public void initialize(){

    }

    @FXML
    public void handleSubmitAction(ActionEvent event){
        Employee newEmployee = new Employee(
                firstNameInput.getText(),
                lastNameInput.getText(),
                new Roles(roleInput.getText(), false, false, false),
                usernameInput.getText(),
                passwordInput.getText(),
                emailInput.getText(),
                phoneInput.getText()
        );

        this.employeeService.addEmployee(newEmployee);
        this.newUserStage.close();
    }
}
