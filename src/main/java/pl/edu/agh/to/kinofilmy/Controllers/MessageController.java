package pl.edu.agh.to.kinofilmy.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class MessageController {

    private Stage messageStage;

    private StringProperty message = new SimpleStringProperty();

    @FXML
    private Label messageText;

    @FXML
    private Button okButton;

    @FXML
    private void initialize(){
        messageText.textProperty().bind(message);
    }

    @FXML
    public void handleOkAction(ActionEvent event){
        messageStage.close();
    }

    public void setMessageStage(Stage messageStage) {
        this.messageStage = messageStage;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }


}
