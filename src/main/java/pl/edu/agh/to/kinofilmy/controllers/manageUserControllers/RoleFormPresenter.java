package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

@Controller
public class RoleFormPresenter {

    private Stage roleFormStage;

    private Roles role;

    private boolean approved;

    @FXML
    private TextField roleNameText;

    @FXML
    private CheckBox sellTicketsBox;

    @FXML
    private CheckBox checkTicketsBox;

    @FXML
    private CheckBox manageCinemaBox;

    @FXML
    private CheckBox getStatsBox;

    @FXML
    private CheckBox manageUsersBox;

    @FXML
    private CheckBox manageRolesBox;

    public void setRoleFormStage(Stage roleFormStage) {
        this.roleFormStage = roleFormStage;
    }

    public void setRole(Roles role, boolean newRole) {
        this.role = role;
        if(!newRole) updateDataValues();
    }

    private void updateModel(){
        role.setRoleName(roleNameText.getText());
        role.setSellTickets(sellTicketsBox.isSelected());
        role.setCheckTickets(checkTicketsBox.isSelected());
        role.setManageCinema(manageCinemaBox.isSelected());
        role.setGetStatistics(getStatsBox.isSelected());
        role.setManageUsers(manageUsersBox.isSelected());
        role.setManageRoles(manageRolesBox.isSelected());
    }

    private void updateDataValues() {
        if(role != null){
            roleNameText.textProperty().setValue(role.getRoleName());
            sellTicketsBox.selectedProperty().setValue(role.isSellTickets());
            checkTicketsBox.selectedProperty().setValue(role.isCheckTickets());
            manageCinemaBox.selectedProperty().setValue(role.isManageCinema());
            getStatsBox.selectedProperty().setValue(role.isGetStatistics());
            manageUsersBox.selectedProperty().setValue(role.isManageUsers());
            manageRolesBox.selectedProperty().setValue(role.isManageRoles());
        }
    }

    @FXML
    private void handleOKAction(ActionEvent event){
        updateModel();
        approved = true;
        roleFormStage.close();
    }

    public boolean isApproved() {
        return approved;
    }
}
