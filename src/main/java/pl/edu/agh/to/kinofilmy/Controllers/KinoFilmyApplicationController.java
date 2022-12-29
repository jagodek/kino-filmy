package pl.edu.agh.to.kinofilmy.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.edu.agh.to.kinofilmy.Model.Roles.Roles;

import java.io.IOException;

public class KinoFilmyApplicationController {

    private Stage primaryStage;

    private Roles userRole;

    public KinoFilmyApplicationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout(){
        try {
            this.primaryStage.setTitle("Kino-Filmy");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KinoFilmyApplicationController.class.getResource("/view/mainView.fxml"));
            BorderPane rootLayout = loader.load();

            // set initial data into controller
            MainController controller = loader.getController();
            controller.setApplicationController(this);

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            this.userRole = login();
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }

    public Roles login(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KinoFilmyApplicationController.class.getResource("/view/loginView.fxml"));
            BorderPane page = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Log in");
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            loginStage.setScene(scene);

            LoginPresenter presenter = loader.getController();
            presenter.setLoginStage(loginStage);

            loginStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Roles("None", false, false, false);
    }


}
