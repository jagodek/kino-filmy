package pl.edu.agh.to.kinofilmy.controllers.managesScreenControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.to.kinofilmy.model.screen.Screen;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenService;

@Controller
public class NewScreenController {

    private final ScreenService screenService;


    private Stage newScreenStage;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField seatsNumberInput;

    @FXML
    private TextField rowNumberInput;

    @FXML
    private Button submit;

    public NewScreenController(ScreenService screenService){
        this.screenService = screenService;
    }

    public void setNewScreenStage(Stage newScreenStage) {
        this.newScreenStage = newScreenStage;
    }

    @FXML
    public void initialize(){

    }

    @FXML
    public void handleSubmitAction(ActionEvent event){
        Screen screen = new Screen(
                nameInput.getText(),
                Integer.parseInt(seatsNumberInput.getText()),
                Integer.parseInt(rowNumberInput.getText())
        );

        this.screenService.save(screen);
        this.newScreenStage.close();
    }
}
