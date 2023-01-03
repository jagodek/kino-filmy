package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.FilmService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesService;


@Controller
public class MainController {

    private final KinoFilmyApplicationController applicationController;

    private Stage mainStage;



    public MainController(KinoFilmyApplicationController applicationController) {
        this.applicationController = applicationController;

    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private Button newUserButton;

    @FXML
    private Button manageFilmsButton;

    @FXML
    private void initialize(){

    }

    @FXML
    public void handleNewUserAction(ActionEvent event){
        applicationController.showNewUserForm(mainStage);
    }

    @FXML
    public void handleManageFilmsAction(ActionEvent event){
        applicationController.showFilmManagement(mainStage);
    }


    @FXML
    private Button userManagementButton;

    @FXML
    public void handleManageUsersAction(ActionEvent event){
        applicationController.showUserManagement(mainStage);
    }
}
