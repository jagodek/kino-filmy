package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

@Controller
public class RolesManagementController {

    private Stage rolesManagementStage;


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
    private void handleSaveAction(ActionEvent event){

    }
}
