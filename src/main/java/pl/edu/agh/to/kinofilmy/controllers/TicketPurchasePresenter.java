package pl.edu.agh.to.kinofilmy.controllers;

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

import java.time.Instant;
import java.util.Date;

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
    private void handleBuyAction(){
        Showing showing = this.showingService.showingDisplayToShowing(this.showingsTable.getSelectionModel().getSelectedItem());
        this.ticketService.save(new Ticket(
                showing,
                1L,
                showing.getPrice(),
                Date.from(Instant.now()),
                this.ticketService.getFirstAvailableSeat(showing).getRowNumber(),
                this.ticketService.getFirstAvailableSeat(showing).getSeatNumber(),
                "sold"
        ));
    }

    @FXML
    private void handleCheckAction(){

    }

    @FXML
    private void handleChooseSeatAction(){

    }
}
