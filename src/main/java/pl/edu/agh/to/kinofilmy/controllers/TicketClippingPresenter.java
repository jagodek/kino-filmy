package pl.edu.agh.to.kinofilmy.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.ticket.TicketService;

@Controller
public class TicketClippingPresenter {
    private final TicketService ticketService;
    private final KinoFilmyApplicationController applicationController;

    private Stage stage;

    @FXML
    private TextField ticketIdInput;
    @FXML
    private Button clipButton;

    public TicketClippingPresenter(TicketService ticketService, KinoFilmyApplicationController applicationController) {
        this.ticketService = ticketService;
        this.applicationController = applicationController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize(){
        this.clipButton.disableProperty().bind(Bindings.isNull(ticketIdInput.textProperty()));
    }

    @FXML
    private void handleClipAction(){
        boolean result = this.ticketService.clipTicket(Long.parseLong(this.ticketIdInput.getText()));
        if(result){
            applicationController.displayMessage(stage, "Ticket is clipped");
        }
        else{
            applicationController.displayMessage(stage, "Failed to clip the ticket");
        }
        this.stage.close();
    }
}
