package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

@Controller
public class UserManagementPresenter {
    private Stage userManagementStage;
    @Autowired
    private KinoFilmyApplicationController applicationController;
    @Autowired
    private EmployeeService employeeService;

    @FXML
    private TableView<Employee> usersTable;
    @FXML
    private TableColumn<Employee, String> firstnameColumn;
    @FXML
    private TableColumn<Employee, String> lastnameColumn;
    @FXML
    private TableColumn<Employee, Roles> roleColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, String> phoneNumberColumn;
    @FXML
    private Button detailsButton;
    @FXML
    private Button addUserButton;

    @FXML
    private void initialize(){
        this.usersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.firstnameColumn.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getFirstname()));
        this.lastnameColumn.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getLastname()));
        this.roleColumn.setCellValueFactory(val -> new SimpleObjectProperty<>(val.getValue().getRole()));
        this.emailColumn.setCellValueFactory(val -> new SimpleObjectProperty<>(val.getValue().getEmail()));
        this.phoneNumberColumn.setCellValueFactory(val -> new SimpleObjectProperty<>(val.getValue().getEmail()));
        this.usersTable.setItems(FXCollections.observableArrayList(employeeService.getEmployees()));
    }

    public void setUserManagementStage(Stage userManagementStage) {
        this.userManagementStage = userManagementStage;
    }

    @FXML
    private void handleAddUserAction(){
        applicationController.showAddUserForm(userManagementStage);
    }

    @FXML
    private void handleEditUserAction(){
        applicationController.showEditUserForm(userManagementStage, this.usersTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleDeleteUserAction(){
        employeeService.deleteEmployee(this.usersTable.getSelectionModel().getSelectedItem());
    }
}
