package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.email.EmailService;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;

@Controller
public class NotificationController {

    private EmployeeService employeeService;

    private KinoFilmyApplicationController applicationController;

    private EmailService emailService;

    private Stage notificationStage;

    public NotificationController(EmployeeService employeeService, KinoFilmyApplicationController applicationController, EmailService emailService) {
        this.employeeService = employeeService;
        this.applicationController = applicationController;
        this.emailService = emailService;
    }

    @FXML
    private TextArea notificationTextArea;

    @FXML
    private TextField subjectTextField;

    @FXML
    private void handleSendAction(ActionEvent event){

        if(emailService.sendNotificationToAllEmployees(subjectTextField.getText(), notificationTextArea.getText())){
            applicationController.displayMessage(notificationStage, "Message sent successfully!");
            notificationStage.close();
        } else {
            applicationController.displayMessage(notificationStage, "Something went wrong when sending message!");
        }


    }


    public void setNotificationStage(Stage notificationStage) {
        this.notificationStage = notificationStage;
    }
}
