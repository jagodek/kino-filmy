package pl.edu.agh.to.kinofilmy.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

import java.io.IOException;

@Controller
public class KinoFilmyApplicationController implements ApplicationContextAware {
    private Stage primaryStage;

    private Roles userRole;

    private ApplicationContext applicationContext;


    public void initRootLayout(){
        try {
            this.primaryStage.setTitle("Kino-Filmy");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KinoFilmyApplicationController.class.getResource("/view/mainView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane rootLayout = loader.load();

            // set initial data into controller
            MainController controller = loader.getController();
            controller.setMainStage(primaryStage);

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            login();
            if(userRole != null){
                primaryStage.show();
            }
        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }

    public void login(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KinoFilmyApplicationController.class.getResource("/view/loginView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane page = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Log in");
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            loginStage.setScene(scene);
            LoginController controller = loader.getController();
            controller.setLoginStage(loginStage);

            loginStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayMessage(Stage parentStage, String message){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KinoFilmyApplicationController.class.getResource("/view/messageView.fxml"));
            BorderPane page = loader.load();

            Stage messageStage = new Stage();
            messageStage.setTitle("Message");
            messageStage.initModality(Modality.WINDOW_MODAL);
            messageStage.initOwner(parentStage);
            Scene scene = new Scene(page);
            messageStage.setScene(scene);

            MessageController presenter = loader.getController();
            presenter.setMessage(message);
            presenter.setMessageStage(messageStage);

            messageStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewUserForm(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/newUserView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            GridPane layout = loader.load();

            Stage addUserStage = new Stage();
            addUserStage.setTitle("Add new user");
            addUserStage.initModality(Modality.WINDOW_MODAL);
            addUserStage.initOwner(parent);
            NewUserController controller = loader.getController();
            controller.setNewUserStage(addUserStage);
            Scene scene = new Scene(layout);
            addUserStage.setScene(scene);
            addUserStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFilmManagement(Stage parent){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/filmManagementView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage manageFilmsStage = new Stage();
            manageFilmsStage.setTitle("Film management");
            manageFilmsStage.initModality(Modality.NONE);
            manageFilmsStage.initOwner(parent);
            FilmManagementController controller = loader.getController();
            controller.setFilmManagementStage(manageFilmsStage);
            Scene scene = new Scene(layout);
            manageFilmsStage.setScene(scene);
            manageFilmsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showNewFilmForm(Stage parent){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/filmFormView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage filmFormStage = new Stage();
            filmFormStage.setTitle("Add new film");
            filmFormStage.initModality(Modality.WINDOW_MODAL);
            filmFormStage.initOwner(parent);
            FilmFormPresenter presenter = loader.getController();
            presenter.setFilmFormStage(filmFormStage);
            Scene scene = new Scene(layout);
            filmFormStage.setScene(scene);
            filmFormStage.showAndWait();
            return presenter.isApproved();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Roles getUserRole() {
        return userRole;
    }

    public void setUserRole(Roles userRole) {
        this.userRole = userRole;
    }



}
