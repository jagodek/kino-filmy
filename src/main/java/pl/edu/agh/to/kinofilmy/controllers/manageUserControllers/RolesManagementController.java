package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesService;

@Controller
public class RolesManagementController {

    private Stage rolesManagementStage;

    private final RolesService rolesService;

    @FXML
    private TableView<Roles> rolesTable;

    @FXML
    private TableColumn<Roles, Long> idColumn;

    @FXML
    private TableColumn<Roles, String> roleNameColumn;

    @FXML
    private TableColumn<Roles, Boolean> manageUsersColumn;

    @FXML
    private TableColumn<Roles, Boolean> sellTicketsColumn;

    @FXML
    private TableColumn<Roles, Boolean> checkTicketsColumn;

    @FXML
    private TableColumn<Roles, Boolean> manageRolesColumn;

    @FXML
    private TableColumn<Roles, Boolean> manageCinemaColumn;

    @FXML
    private TableColumn<Roles, Boolean> getStatsColumn;

    public RolesManagementController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @FXML
    public void initialize(){
        rolesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        idColumn.setCellValueFactory(rolesValue -> new SimpleLongProperty(rolesValue.getValue().getId()).asObject());
        roleNameColumn.setCellValueFactory(rolesValue -> new SimpleStringProperty(rolesValue.getValue().getRoleName()));
        manageUsersColumn.setCellValueFactory(rolesValue -> new SimpleBooleanProperty(rolesValue.getValue().isManageUsers()));
        manageUsersColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
        sellTicketsColumn.setCellValueFactory(rolesValue -> new SimpleBooleanProperty(rolesValue.getValue().isSellTickets()));
        sellTicketsColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
        checkTicketsColumn.setCellValueFactory(rolesValue -> new SimpleBooleanProperty(rolesValue.getValue().isCheckTickets()));
        checkTicketsColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
        manageRolesColumn.setCellValueFactory(rolesValue -> new SimpleBooleanProperty(rolesValue.getValue().isManageRoles()));
        manageRolesColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
        manageCinemaColumn.setCellValueFactory(rolesValue -> new SimpleBooleanProperty(rolesValue.getValue().isManageCinema()));
        manageCinemaColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
        getStatsColumn.setCellValueFactory(rolesValue -> new SimpleBooleanProperty(rolesValue.getValue().isGetStatistics()));
        getStatsColumn.setCellFactory(tc -> new CheckBoxTableCell<>());

        resetData();
    }

    private void resetData(){
        rolesTable.getSelectionModel().clearSelection();
        rolesTable.setItems(FXCollections.observableArrayList(rolesService.findAll()));
    }

    public void setRolesManagementStage(Stage rolesManagementStage) {
        this.rolesManagementStage = rolesManagementStage;
    }

    @FXML
    private void handleResetAction(ActionEvent event){

    }

    @FXML
    private void handleAddAction(ActionEvent event){

    }

    @FXML
    private void handleDeleteAction(ActionEvent event){

    }

    @FXML
    private void handleEditAction(ActionEvent event){

    }
}
