package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeDisplay;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

@Controller
public class UserManagementPresenter {
    private Stage userManagementStage;
    private final KinoFilmyApplicationController applicationController;
    private final EmployeeService employeeService;

    private ObservableList<EmployeeDisplay> employees;
    @FXML
    private TableView<EmployeeDisplay> usersTable;
    @FXML
    private TableColumn<EmployeeDisplay, String> firstnameColumn;
    @FXML
    private TableColumn<EmployeeDisplay, String> lastnameColumn;
    @FXML
    private TableColumn<EmployeeDisplay, Roles> roleColumn;
    @FXML
    private TableColumn<EmployeeDisplay, String> emailColumn;
    @FXML
    private TableColumn<EmployeeDisplay, String> phoneNumberColumn;
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
        this.firstnameColumn.setCellValueFactory(val -> val.getValue().firstnameProperty());
        this.lastnameColumn.setCellValueFactory(val -> val.getValue().lastnameProperty());
        this.roleColumn.setCellValueFactory(val -> val.getValue().roleProperty());
        this.emailColumn.setCellValueFactory(val -> val.getValue().emailProperty());
        this.phoneNumberColumn.setCellValueFactory(val -> val.getValue().phoneNumberProperty());

        deleteButton.disableProperty().bind(Bindings.isEmpty(usersTable.getSelectionModel().getSelectedItems()));
        detailsButton.disableProperty().bind(Bindings.isEmpty(usersTable.getSelectionModel().getSelectedItems()));
        refresh();
    }

    public void setUserManagementStage(Stage userManagementStage) {
        this.userManagementStage = userManagementStage;
    }

    @FXML
    private void handleAddUserAction(){
        applicationController.showNewUserForm(userManagementStage);
        refresh();
    }

    @FXML
    private void handleEditUserAction(){
        if(this.usersTable.getSelectionModel().getSelectedItem() != null){
            applicationController.showEditUserForm(
                    userManagementStage,
                    employeeService.employeeFromEmployeeDisplay(this.usersTable.getSelectionModel().getSelectedItem()));
            refresh();
        }
    }

    @FXML
    private void handleDeleteUserAction(){
        employeeService.deleteEmployee(employeeService.employeeFromEmployeeDisplay(
                this.usersTable.getSelectionModel().getSelectedItem()));
        refresh();
    }

    private void refresh(){
        usersTable.getSelectionModel().clearSelection();
        this.employees = employeeService.findAllAsEmployeeDisplay();
        this.usersTable.setItems(this.employees);
    }
}
