package pl.edu.agh.to.kinofilmy.controllers;

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

    private Seat seat;
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
    }

    @FXML
    private void handleBuyAction(ActionEvent event){
        Showing showing = this.showingService.showingDisplayToShowing(this.showingsTable.getSelectionModel().getSelectedItem());
        this.ticketService.save(new Ticket(
                showing,
                applicationController.getUserId(),
                showing.getPrice(),
                Date.from(Instant.now()),
                this.ticketService.getFirstAvailableSeat(showing).getRowNumber(),
                this.ticketService.getFirstAvailableSeat(showing).getSeatNumber(),
                TicketState.Sold.getState()
        ));
    }

    @FXML
    private void handleCheckAction(ActionEvent event){

    }

    @FXML
    private void handleChooseSeatAction(ActionEvent event){

    }

    @FXML
    private void handleShowSuggestedAction(ActionEvent event){
        Optional<Showing> optionalShowing = this.showingService.getSuggested();
        if(optionalShowing.isPresent()){
            List<ShowingDisplay> suggested = new LinkedList<>();
            suggested.add(new ShowingDisplay(optionalShowing.get()));
            this.showingsTable.setItems(FXCollections.observableList(suggested));
        }
        else{
            this.applicationController.displayMessage(this.ticketPurchaseStage, "No suggested seances");
        }

    }
}
