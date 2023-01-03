package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesService;

@Controller
public class NewUserController {

    private final KinoFilmyApplicationController applicationController;

    private final EmployeeService employeeService;

    private final RolesService rolesService;

    private Stage newUserStage;

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

    public NewUserController(KinoFilmyApplicationController applicationController, EmployeeService employeeService, RolesService rolesService){
        this.applicationController = applicationController;
        this.employeeService = employeeService;
        this.rolesService = rolesService;

    }

    public void setNewUserStage(Stage newUserStage) {
        this.newUserStage = newUserStage;
    }

    @FXML
    public void initialize(){
        for (Roles role :
                this.rolesService.findAll()) {

//            new MenuItem().setText(role.getRoleName())
            this.roleInput.getItems().add(role);
        }
        this.roleInput.setOnAction((event -> {
            this.roleInput.setValue(this.roleInput.getValue());
        }));
    }

    @FXML
    public void handleSubmitAction(ActionEvent event){
        Employee newEmployee = new Employee(
                firstNameInput.getText(),
                lastNameInput.getText(),
                roleInput.getValue(),
                usernameInput.getText(),
                passwordInput.getText(),
                emailInput.getText(),
                phoneInput.getText()
        );

        this.employeeService.save(newEmployee);
        this.newUserStage.close();
    }
}
