package pl.edu.agh.to.kinofilmy.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.FilmService;
import pl.edu.agh.to.kinofilmy.model.film.FilmStatisticTickets;

import java.util.Date;
import java.util.Dictionary;

@Controller
public class ShowStatisticsController {

    final FilmService filmService;
    private Stage showStatisticsStage;

    private ObservableList<FilmStatisticTickets> filmList;
    @FXML
    private ChoiceBox<String> TypeOfStatisticsChoiceBox;

    @FXML
    private ChoiceBox<String> TimePeriodChoiceBox;

    @FXML
    private TableView<FilmStatisticTickets> table;

    @FXML
    private TableColumn<FilmStatisticTickets, Long> place;

    @FXML
    private TableColumn<FilmStatisticTickets, String> title;

    @FXML
    private TableColumn<FilmStatisticTickets, Long> ticketsSold;



    private String[] timePeriodsNames = {"This Day","This Week","This Month","This Year"};


    public ShowStatisticsController(FilmService filmService){
        this.filmService = filmService;
    }
    @FXML
    public void initialize(){
        this.TypeOfStatisticsChoiceBox.getItems().add("Films by tickets sold");
        for (String val :
                timePeriodsNames) {
            this.TimePeriodChoiceBox.getItems().add(val);
        }
        this.TypeOfStatisticsChoiceBox.setValue(this.TypeOfStatisticsChoiceBox.getItems().get(0) );
        this.TimePeriodChoiceBox.setValue(this.TimePeriodChoiceBox.getItems().get(0) );
        choosenType(this.TypeOfStatisticsChoiceBox.getValue());
    }

    public void setShowStatisticsStage(Stage showStatisticsStage) {
        this.showStatisticsStage = showStatisticsStage;
    }

    public void choosenType(String type){
        if(this.TimePeriodChoiceBox.getValue() == "This Day"){
            this.filmList = this.filmService.getMoviesByTicketsSold();
        }
    }

}
