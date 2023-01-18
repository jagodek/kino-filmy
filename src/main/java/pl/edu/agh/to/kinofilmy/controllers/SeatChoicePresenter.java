package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.screen.Seat;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;
import pl.edu.agh.to.kinofilmy.model.ticket.TicketService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class SeatChoicePresenter {
    private final TicketService ticketService;
    private Showing showing;
    private List<Seat> selectedSeatsList;

    private Stage seatChoiceStage;
    private Pane[][] seats;
    private final Set<Seat> selectedSeats = new TreeSet<>();
    private int rows;
    private int seatsInRow;

    private final Background occupiedBackground = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));
    private final Background freeBackground = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
    private final Background unavailableBackground = new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));
    private final Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

    @FXML
    private GridPane seatsDisplay;
    @FXML
    private Button refreshButton;
    @FXML
    private Button selectButton;

    public SeatChoicePresenter(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public void setStage(Stage seatChoiceStage) {
        this.seatChoiceStage = seatChoiceStage;
    }

    public void setSelectedSeatsList(List<Seat> selectedSeatsList) {
        this.selectedSeatsList = selectedSeatsList;
    }

    public void initView(){
        int rowNumber = this.showing.getScreen().getRowNumber(), seatsNumber = this.showing.getScreen().getSeatsNumber();
        this.rows = rowNumber;
        this.seatsInRow = seatsNumber / rowNumber;
        int biggerRows;
        int seatsInLastRow;
        if(seatsNumber % rowNumber == 0){
            seatsInLastRow = this.seatsInRow;
            biggerRows = 0;
        }
        else{
            seatsInLastRow = this.seatsInRow;
            this.seatsInRow++;
            biggerRows = seatsNumber % rowNumber;
        }
        this.seats = new Pane[rows][seatsInRow];

        System.out.println(rows + " " + seatsInRow + " " + seatsInLastRow + " " + biggerRows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                seats[i][j] = new Pane();
                seats[i][j].setPrefSize(20, 20);
                seats[i][j].setBorder(border);
                seats[i][j].setOnMouseClicked(
                        new SelectSeatEventHandler(
                                seats[i][j], this.selectedSeats, this.showing.getScreen(),
                                i, j, true));
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                seats[i][j].setBackground(freeBackground);
            }
        }

        if(seatsNumber % rowNumber != 0){
            for (int i = rows-1; i >= biggerRows; i--) {
                seats[i][seatsInRow-1].setBackground(unavailableBackground);
                seats[i][seatsInRow-1].setOnMouseClicked(
                        new SelectSeatEventHandler(
                                seats[i][seatsInRow-1], this.selectedSeats, this.showing.getScreen(),
                                i, seatsInRow-1, false)
                );
            }
        }


        for (int i = 0; i < rows; i++) {
            this.seatsDisplay.getRowConstraints().add(new RowConstraints(20));
        }
        for (int j = 0; j < seatsInRow; j++) {
            this.seatsDisplay.getColumnConstraints().add(new ColumnConstraints(20));
        }
        this.seatsDisplay.setAlignment(Pos.CENTER);
        updateView();
    }

    private void updateView(){
        this.ticketService.getOccupiedSeats(this.showing).forEach(seat -> {
            int i = seat.getRowNumber()-1, j = seat.getSeatNumber()-1;
            seats[i][j].setBackground(occupiedBackground);
            seats[i][j].setOnMouseClicked(new SelectSeatEventHandler(seats[i][j], this.selectedSeats, seat, false));
        });
        this.seatsDisplay.getChildren().clear();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                this.seatsDisplay.add(seats[i][j], j, i);
            }
        }
    }

    @FXML
    private void handleRefreshAction(ActionEvent event){
        this.updateView();
    }
    @FXML
    private void handleConfirmAction(ActionEvent event){
        this.selectedSeatsList.clear();
        this.selectedSeatsList.addAll(this.selectedSeats);
        this.seatChoiceStage.close();
    }


}
