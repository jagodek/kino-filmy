package pl.edu.agh.to.kinofilmy.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.screen.Seat;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;
import pl.edu.agh.to.kinofilmy.model.showing.ShowingDisplay;
import pl.edu.agh.to.kinofilmy.model.showing.ShowingService;
import pl.edu.agh.to.kinofilmy.model.ticket.Ticket;
import pl.edu.agh.to.kinofilmy.model.ticket.TicketService;
import pl.edu.agh.to.kinofilmy.model.ticket.TicketState;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class TicketPurchasePresenter {
    private final TicketService ticketService;
    private final ShowingService showingService;
    private final KinoFilmyApplicationController applicationController;
    private Stage ticketPurchaseStage;
    private final List<Seat> selectedSeats = new LinkedList<>();
    @FXML
    private TableView<ShowingDisplay> showingsTable;
    @FXML
    private TableColumn<ShowingDisplay, String> filmTitleColumn;
    @FXML
    private TableColumn<ShowingDisplay, String> screenNameColumn;
    @FXML
    private TableColumn<ShowingDisplay, Date> dateColumn;
    @FXML
    private TableColumn<ShowingDisplay, Float> priceColumn;
    @FXML
    private Button filmDetails;
    @FXML
    private Button buyButton;
    @FXML
    private Button chooseSeatButton;
    @FXML
    private Button getSuggestedButton;

    public TicketPurchasePresenter(TicketService ticketService, ShowingService showingService, KinoFilmyApplicationController applicationController) {
        this.ticketService = ticketService;
        this.showingService = showingService;
        this.applicationController = applicationController;
    }

    public void setTicketPurchaseStage(Stage ticketPurchaseStage) {
        this.ticketPurchaseStage = ticketPurchaseStage;
    }

    @FXML
    public void initialize(){
        this.showingsTable.setItems(this.showingService.findAllShowingDisplay());
        this.showingsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.filmTitleColumn.setCellValueFactory(val -> val.getValue().filmTitleProperty());
        this.screenNameColumn.setCellValueFactory(val -> val.getValue().screenNameProperty());
        this.dateColumn.setCellValueFactory(val -> val.getValue().dateProperty());
        this.priceColumn.setCellValueFactory(val -> val.getValue().priceProperty());

        this.filmDetails.disableProperty().bind(Bindings.isEmpty(showingsTable.getSelectionModel().getSelectedItems()));
        this.buyButton.disableProperty().bind(Bindings.isEmpty(showingsTable.getSelectionModel().getSelectedItems()));
        this.chooseSeatButton.disableProperty().bind(Bindings.isEmpty(showingsTable.getSelectionModel().getSelectedItems()));
    }

    @FXML
    private void handleBuyAction(ActionEvent event){
        Showing showing = this.showingService.showingDisplayToShowing(this.showingsTable.getSelectionModel().getSelectedItem());
        if(selectedSeats.size() == 0){
            Seat seat = this.ticketService.getFirstAvailableSeat(showing);
            if(seat == null){
                applicationController.displayMessage(ticketPurchaseStage,
                        "All ticket for this showing have been sold");
            }
            else{
                Long id = this.ticketService.save(new Ticket(
                        showing,
                        applicationController.getUserId(),
                        showing.getPrice(),
                        Date.from(Instant.now()),
                        seat.getRowNumber(),
                        seat.getSeatNumber(),
                        TicketState.Sold.getState()
                ));
                applicationController.displayMessage(ticketPurchaseStage,
                        "Transaction successful. Your ticket identifier is "+id);
            }
        }
        else{
            List<Long> ticketIds = new LinkedList<>();
            selectedSeats.forEach(seat -> {
                ticketIds.add(this.ticketService.save(new Ticket(
                        showing,
                        applicationController.getUserId(),
                        showing.getPrice(),
                        Date.from(Instant.now()),
                        seat.getRowNumber(),
                        seat.getSeatNumber(),
                        TicketState.Sold.getState()
                )));
            });
            final String[] msg = {"Transaction successful. Identifiers of your tickets are: "};
            ticketIds.forEach(id -> msg[0] = msg[0] + id.toString() +" ");
            applicationController.displayMessage(ticketPurchaseStage, msg[0]);
        }
    }

    @FXML
    private void handleCheckAction(ActionEvent event){
        this.applicationController.displayMessage(ticketPurchaseStage,
                showingService.showingDisplayToShowing(showingsTable.getSelectionModel().getSelectedItems().get(0))
                        .toString());
    }

    @FXML
    private void handleChooseSeatAction(ActionEvent event){
        this.applicationController.showSeatChoiceView(ticketPurchaseStage,
                this.showingService.showingDisplayToShowing(this.showingsTable.getSelectionModel().getSelectedItem()),
                this.selectedSeats);
    }

    @FXML
    private void handleShowSuggestedAction(ActionEvent event){
        List<Showing> suggestedShowings = this.showingService.getSuggested();
        if(suggestedShowings.size() > 0){
            List<ShowingDisplay> suggested = suggestedShowings.stream().map(ShowingDisplay::new).toList();
            this.showingsTable.setItems(FXCollections.observableList(suggested));
        }
        else{
            this.applicationController.displayMessage(this.ticketPurchaseStage, "No suggested seances");
        }
    }
}
