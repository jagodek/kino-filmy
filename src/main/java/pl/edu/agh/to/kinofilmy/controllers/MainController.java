package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;


@Controller
public class MainController {

    private final KinoFilmyApplicationController applicationController;

    private Stage mainStage;
    private Roles roles;


    public MainController(KinoFilmyApplicationController applicationController) {
        this.applicationController = applicationController;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
        initialize();
    }

    @FXML
    private Button manageUsersButton;

    @FXML
    private Button manageFilmsButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button manageScreensButton;

    @FXML
    private Button manageShowingsButton;

    @FXML
    private Button showStatisticsButton;

    @FXML
    private Button buyTicketButton;

    @FXML
    private Button clipTicketButton;

    @FXML
    private void initialize() {
        if (this.roles != null) {
            if (!roles.isManageUsers()) {
                manageUsersButton.setDisable(true);
                manageUsersButton.setVisible(false);
            }
            if (!roles.isManageCinema()) {
                manageScreensButton.setDisable(true);
                manageScreensButton.setVisible(false);
                manageFilmsButton.setDisable(true);
                manageFilmsButton.setVisible(false);
                manageShowingsButton.setDisable(true);
                manageShowingsButton.setVisible(false);
            }
            if(!roles.isCheckTickets()){
                clipTicketButton.setDisable(true);
                clipTicketButton.setVisible(false);
            }
            if(!roles.isGetStatistics()){
                showStatisticsButton.setDisable(true);
                showStatisticsButton.setVisible(false);
            }
            if(!roles.isSellTickets()){
                buyTicketButton.setDisable(true);
                buyTicketButton.setVisible(false);
            }
        }
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


    public void logOutAction(){
        applicationController.logOut();
    }

    @FXML
    public void handleManageScreensAction(ActionEvent event){applicationController.showScreenManagement(mainStage);}

    @FXML
    public void handleManageShowingAction(ActionEvent event){applicationController.showShowingManagement(mainStage);}

    @FXML
    public void handleShowStatisticsAction(ActionEvent event){applicationController.showStatistics(mainStage);}

    @FXML
    public void handleBuyTicketAction(ActionEvent event){applicationController.showTicketPurchaseView(mainStage);}
    @FXML
    public void handleClipTicketAction(ActionEvent event){applicationController.showTicketClippingView(mainStage);}
}
