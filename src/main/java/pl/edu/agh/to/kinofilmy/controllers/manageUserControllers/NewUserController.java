package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesService;

import java.util.Optional;

@Controller
public class NewUserController {
    @Autowired
    private KinoFilmyApplicationController applicationController;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RolesService rolesService;


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

    public void setNewUserStage(Stage newUserStage) {
        this.newUserStage = newUserStage;
    }

    @FXML
    public void initialize(){

    }

    @FXML
    public void handleSubmitAction(ActionEvent event){
        Optional<Roles> rolesOptional = rolesService.getRole(roleInput.getText());
        if(rolesOptional.isPresent()){
            Employee newEmployee = new Employee(
                    firstNameInput.getText(),
                    lastNameInput.getText(),
                    rolesOptional.get(),
                    usernameInput.getText(),
                    passwordInput.getText(),
                    emailInput.getText(),
                    phoneInput.getText()
            );
            this.employeeService.addEmployee(newEmployee);
            this.newUserStage.close();
        }
        else {
            applicationController.displayMessage(newUserStage, "Wrong name of role");
        }



    }
}
