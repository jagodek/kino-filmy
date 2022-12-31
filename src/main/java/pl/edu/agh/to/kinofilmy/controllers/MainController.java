package pl.edu.agh.to.kinofilmy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @Autowired
    private KinoFilmyApplicationController applicationController;


    @FXML
    private Button addUserButton;

    @FXML
    public void addUserView(){
        this.applicationController.addUserView();
    }


}