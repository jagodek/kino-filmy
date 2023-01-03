package pl.edu.agh.to.kinofilmy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.util.Dictionary;

@Controller
public class ShowStatisticsController {

    private Stage showStatisticsStage;
    @FXML
    private ChoiceBox<String> TypeOfStatisticsChoiceBox;

    @FXML
    private ChoiceBox<String> TimePeriodChoiceBox;

    private String[] timePeriodsNames = {"This Day","This Week","This Month","This Year"};

    @FXML
    public void initialize(){
        this.TypeOfStatisticsChoiceBox.getItems().add("Films by tickets sold");
        for (String val :
                timePeriodsNames) {
            this.TimePeriodChoiceBox.getItems().add(val);
        }
        this.TypeOfStatisticsChoiceBox.setValue(this.TypeOfStatisticsChoiceBox.getItems().get(0) );
        this.TimePeriodChoiceBox.setValue(this.TimePeriodChoiceBox.getItems().get(0) );
    }

    public void setShowStatisticsStage(Stage showStatisticsStage) {
        this.showStatisticsStage = showStatisticsStage;
    }

}
