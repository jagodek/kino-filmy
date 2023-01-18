package pl.edu.agh.to.kinofilmy.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.FilmStatisticTickets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.awt.*;
import java.util.List;

import static java.lang.Math.*;

@Controller
public class ChartController {

    private final KinoFilmyApplicationController applicationController;
    private Stage showStatisticsStage;
    private ObservableList<FilmStatisticTickets> data;
    @FXML
    public BarChart chart;

    @FXML
    public CategoryAxis xAxis;

    @FXML
    public NumberAxis yAxis;

    @FXML
    private Label title;
    public ChartController(KinoFilmyApplicationController applicationController) {
        this.applicationController = applicationController;
    }

    public void initialize(){

    }

    public void setShowChartStage(Stage showStatisticsStage, ObservableList<FilmStatisticTickets> data,String range) {
        this.showStatisticsStage = showStatisticsStage;
        this.data= data;
        XYChart.Series series = new XYChart.Series();
        for (FilmStatisticTickets obj:this.data) {
            series.getData().add(new XYChart.Data(obj.getString(),obj.getTicketsSold()));

        }
        this.chart.getData().add(series);

        if(data.size()==1)
            chart.setCategoryGap(300);
        else{
            chart.setCategoryGap(20);
        }

        this.chart.setScaleY(1);

        xAxis.setTickLabelRotation(65);
        this.title.setText("tickets sold "+range);


    }

}

