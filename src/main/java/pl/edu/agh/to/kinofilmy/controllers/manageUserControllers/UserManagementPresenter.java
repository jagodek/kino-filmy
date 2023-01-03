package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
    private final KinoFilmyApplicationController applicationController;
    private final EmployeeService employeeService;

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
    private Button deleteButton;
    @FXML
    private Button refreshButton;

    public UserManagementPresenter(KinoFilmyApplicationController applicationController, EmployeeService employeeService) {
        this.applicationController = applicationController;
        this.employeeService = employeeService;
    }

    @FXML
    private void initialize(){
        this.usersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.firstnameColumn.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getFirstname()));
        this.lastnameColumn.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getLastname()));
        this.roleColumn.setCellValueFactory(val -> new SimpleObjectProperty<>(val.getValue().getRole()));
        this.emailColumn.setCellValueFactory(val -> new SimpleObjectProperty<>(val.getValue().getEmail()));
        this.phoneNumberColumn.setCellValueFactory(val -> new SimpleObjectProperty<>(val.getValue().getEmail()));
        refreshEmployeeData();

        detailsButton.disableProperty().bind(Bindings.isEmpty(usersTable.getSelectionModel().getSelectedItems()));
        deleteButton.disableProperty().bind(Bindings.isEmpty(usersTable.getSelectionModel().getSelectedItems()));
        //TODO usunięcie możliwości usunięcia użytkownika root
    }

    public void setUserManagementStage(Stage userManagementStage) {
        this.userManagementStage = userManagementStage;
    }

    private void refreshEmployeeData(){
        usersTable.getSelectionModel().clearSelection();
        this.usersTable.setItems(FXCollections.observableArrayList(employeeService.getEmployees()));
    }

    @FXML
    private void handleAddUserAction(){
        applicationController.showNewUserForm(userManagementStage);
        refreshEmployeeData();
    }

    @FXML
    private void handleEditUserAction(){
        applicationController.showEditUserForm(userManagementStage, this.usersTable.getSelectionModel().getSelectedItem());
        refreshEmployeeData();
    }

    @FXML
    private void handleRefreshAction(ActionEvent event){
        refreshEmployeeData();
    }

    @FXML
    private void handleDeleteUserAction(){
        employeeService.deleteEmployee(this.usersTable.getSelectionModel().getSelectedItem());
        refreshEmployeeData();
    }

    @FXML
    private void handleManageRolesAction(ActionEvent event){

    }
}
