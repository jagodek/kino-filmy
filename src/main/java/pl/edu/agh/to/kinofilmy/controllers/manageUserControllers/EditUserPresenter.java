package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesService;

@Controller
public class EditUserPresenter {
    private final EmployeeService employeeService;
    private final RolesService rolesService;

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

    public EditUserPresenter(EmployeeService employeeService, RolesService rolesService) {
        this.employeeService = employeeService;
        this.rolesService = rolesService;
    }

    public void setStage(Stage stage) {
        this.editUserStage = stage;
    }
    public void setEmployee(Employee employee){
        this.user = employee;
        firstNameInput.setText(this.user.getFirstname());
        lastNameInput.setText(this.user.getLastname());
        emailInput.setText(this.user.getEmail());
        phoneInput.setText(this.user.getPhoneNumber());
        usernameInput.setText(this.user.getUsername());
        passwordInput.setText(this.user.getPassword());
    }

    @FXML
    public void initialize(){
        for (Roles role : this.rolesService.findAll()) {
            this.roleInput.getItems().add(role);
        }
        this.roleInput.setOnAction((event -> {
            this.roleInput.setValue(this.roleInput.getValue());
        }));
    }

    @FXML
    public void handleSubmitAction(ActionEvent event){
        this.user.setFirstname(firstNameInput.getText());
        this.user.setLastname(lastNameInput.getText());
        this.user.setRole(roleInput.getValue());
        this.user.setUsername(usernameInput.getText());
        this.user.setPassword(passwordInput.getText());
        this.user.setEmail(emailInput.getText());
        this.user.setPhoneNumber(phoneInput.getText());
        this.employeeService.update(user);
        this.editUserStage.close();
    }
}
