package pl.edu.agh.to.kinofilmy.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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



    private String[] statisticTypeNames = {"Films by tickets sold","Most watched genres","Most watched director"};
    private String[] timePeriodsNames = {"This Day","This Month","This Year"};


    public ShowStatisticsController(FilmService filmService){
        this.filmService = filmService;
    }

    @FXML
    public void initialize(){
        this.place.setCellValueFactory(new PropertyValueFactory<>("Place"));
        this.title.setCellValueFactory(new PropertyValueFactory<>("String"));
        this.ticketsSold.setCellValueFactory(new PropertyValueFactory<>("ticketsSold"));
        for (String val :
                statisticTypeNames) {
            this.TypeOfStatisticsChoiceBox.getItems().add(val);
        }

        for (String val :
                timePeriodsNames) {
            this.TimePeriodChoiceBox.getItems().add(val);
        }
        this.TypeOfStatisticsChoiceBox.setOnAction((event -> {
            choosenType();
        }));
        this.TimePeriodChoiceBox.setOnAction((event -> {
            choosenType();
        }));
        this.TypeOfStatisticsChoiceBox.setValue(this.TypeOfStatisticsChoiceBox.getItems().get(0) );
        this.TimePeriodChoiceBox.setValue(this.TimePeriodChoiceBox.getItems().get(0) );
        choosenType();

    }

    public void setShowStatisticsStage(Stage showStatisticsStage) {
        this.showStatisticsStage = showStatisticsStage;
    }

    public void choosenType(){
        if(this.TypeOfStatisticsChoiceBox.getValue() == "Films by tickets sold") {
            this.table.setItems(this.filmService.getMoviesByTicketsSold(this.TimePeriodChoiceBox.getValue()));
            this.table.refresh();

        }
        if(this.TypeOfStatisticsChoiceBox.getValue() == "Most watched director") {
            this.table.setItems(this.filmService.getMoviesByDirectorDay(this.TimePeriodChoiceBox.getValue()));
            this.table.refresh();

        }

        if(this.TypeOfStatisticsChoiceBox.getValue() == "Most watched genres") {
            this.table.setItems(this.filmService.getMoviesByGenre(this.TimePeriodChoiceBox.getValue()));
            this.table.refresh();

        }
    }

}
