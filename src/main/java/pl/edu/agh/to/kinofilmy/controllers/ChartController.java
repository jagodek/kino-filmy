package pl.edu.agh.to.kinofilmy.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.FilmStatisticTickets;

import java.util.List;

@Controller
public class ChartController {

    private final KinoFilmyApplicationController applicationController;
    private Stage showStatisticsStage;
    private ObservableList<FilmStatisticTickets> data;
    @FXML
    public BarChart chart;

    public ChartController(KinoFilmyApplicationController applicationController) {
        this.applicationController = applicationController;
    }

    public void initialize(){


    }

    public void setShowChartStage(Stage showStatisticsStage, ObservableList<FilmStatisticTickets> data) {
        this.showStatisticsStage = showStatisticsStage;
        this.data= data;
        XYChart.Series series = new XYChart.Series();
        for (FilmStatisticTickets obj:this.data) {
            series.getData().add(new XYChart.Data(obj.getString(),obj.getTicketsSold()));

        }
        this.chart.getData().add(series);
        this.chart.setCategoryGap(10);
    }

}
