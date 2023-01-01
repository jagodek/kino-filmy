package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class FilmManagementController {

    private Stage filmManagementStage;

    @FXML
    private Button newFilmButton;

    @FXML
    private Button editFilmButton;

    @FXML
    private Button deleteFilmButton;

    public void setFilmManagementStage(Stage filmManagementStage) {
        this.filmManagementStage = filmManagementStage;
    }

    @FXML
    private void handleNewFilmAction(ActionEvent event){

    }

    @FXML
    private void handleEditFilmAction(ActionEvent event){

    }

    @FXML
    private void handleDeleteFilmAction(ActionEvent event){

    }

}
