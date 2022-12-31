package pl.edu.agh.to.kinofilmy.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.edu.agh.to.kinofilmy.model.Employee.EmployeeService;

import java.io.IOException;

public class AppController {
    private final Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showAddUserForm(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("./view/newUser.fxml"));
            GridPane layout = loader.load();
            NewUserController controller = loader.getController();
            controller.setEmployeeService(EmployeeService.getInstance());
            Scene scene = new Scene(layout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO don't do this in common apps
            e.printStackTrace();
        }
    }
}
