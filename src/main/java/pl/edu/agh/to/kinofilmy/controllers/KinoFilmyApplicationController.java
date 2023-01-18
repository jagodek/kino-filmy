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
import pl.edu.agh.to.kinofilmy.controllers.manageShowingControllers.EditShowingController;
import pl.edu.agh.to.kinofilmy.controllers.manageShowingControllers.NewShowingController;
import pl.edu.agh.to.kinofilmy.controllers.manageShowingControllers.ShowingManagementPresenter;
import pl.edu.agh.to.kinofilmy.controllers.manageUserControllers.*;
import pl.edu.agh.to.kinofilmy.controllers.managesScreenControllers.EditScreenController;
import pl.edu.agh.to.kinofilmy.controllers.managesScreenControllers.NewScreenController;
import pl.edu.agh.to.kinofilmy.controllers.managesScreenControllers.ScreenManagementPresenter;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.film.Film;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;

import java.io.IOException;

@Controller
public class KinoFilmyApplicationController implements ApplicationContextAware {
    private Stage primaryStage;

    private Roles userRole;
    private Long userId;

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
            if(controller.getFilmManagementStage() == null) {
                controller.setFilmManagementStage(manageFilmsStage);
                Scene scene = new Scene(layout);
                manageFilmsStage.setScene(scene);
                manageFilmsStage.show();
            } else {
                if(!controller.getFilmManagementStage().isShowing()){
                    controller.setFilmManagementStage(manageFilmsStage);
                    Scene scene = new Scene(layout);
                    manageFilmsStage.setScene(scene);
                    manageFilmsStage.show();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showFilmForm(Stage parent, Film film, boolean newFilm){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/filmFormView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage filmFormStage = new Stage();
            if(newFilm) filmFormStage.setTitle("Add new film");
            else filmFormStage.setTitle("Edit film");
            filmFormStage.initModality(Modality.WINDOW_MODAL);
            filmFormStage.initOwner(parent);
            FilmFormPresenter presenter = loader.getController();
            presenter.setFilmFormStage(filmFormStage);
            presenter.setFilm(film, newFilm);
            Scene scene = new Scene(layout);
            filmFormStage.setScene(scene);
            filmFormStage.showAndWait();
            return presenter.isApproved();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showRoleForm(Stage parent, Roles roles, boolean newRole){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/roleFormView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage roleFormStage = new Stage();
            if(newRole) roleFormStage.setTitle("Add new role");
            else roleFormStage.setTitle("Edit role");
            roleFormStage.initModality(Modality.WINDOW_MODAL);
            roleFormStage.initOwner(parent);
            RoleFormPresenter presenter = loader.getController();
            presenter.setRoleFormStage(roleFormStage);
            presenter.setRole(roles, newRole);
            Scene scene = new Scene(layout);
            roleFormStage.setScene(scene);
            roleFormStage.showAndWait();
            return presenter.isApproved();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showEditUserForm(Stage parent, Employee employee){
        try{
            System.out.println("shoe "+employee);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/editUserView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            GridPane layout = loader.load();

            Stage editUserStage = new Stage();
            editUserStage.setTitle("Edit user");
            editUserStage.initModality(Modality.WINDOW_MODAL);
            editUserStage.initOwner(parent);

            EditUserPresenter presenter = loader.getController();
            presenter.setStage(editUserStage);
            presenter.setEmployee(employee);

            Scene scene = new Scene(layout);
            editUserStage.setScene(scene);
            editUserStage.showAndWait();
        } catch (IOException e) {
            // TODO don't do this in common apps
            e.printStackTrace();
        }
    }


    public void showUserManagement(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/manageUsersView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage userManagementStage = new Stage();
            userManagementStage.setTitle("Manage users");
            userManagementStage.initModality(Modality.WINDOW_MODAL);
            userManagementStage.initOwner(parent);
            UserManagementPresenter presenter = loader.getController();
            presenter.setUserManagementStage(userManagementStage);
            Scene scene = new Scene(layout);
            userManagementStage.setScene(scene);
            userManagementStage.showAndWait();
        } catch (IOException e) {
            // TODO don't do this in common apps
            e.printStackTrace();
        }
    }

    public void showRolesManagement(Stage parent){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/rolesManagementView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage rolesManagementStage = new Stage();
            rolesManagementStage.setTitle("Manage roles");
            rolesManagementStage.initModality(Modality.WINDOW_MODAL);
            rolesManagementStage.initOwner(parent);
            RolesManagementController controller = loader.getController();
            controller.setRolesManagementStage(rolesManagementStage);
            Scene scene = new Scene(layout);
            rolesManagementStage.setScene(scene);
            rolesManagementStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showScreenManagement(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/manageScreensView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage screenManagementStage = new Stage();
            screenManagementStage.setTitle("Manage users");
            screenManagementStage.initModality(Modality.WINDOW_MODAL);
            screenManagementStage.initOwner(parent);
            ScreenManagementPresenter presenter = loader.getController();
            presenter.setScreenManagementStage(screenManagementStage);
            Scene scene = new Scene(layout);
            screenManagementStage.setScene(scene);
            screenManagementStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewScreenForm(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/newScreenForm.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            GridPane layout = loader.load();

            Stage addScreenStage = new Stage();
            addScreenStage.setTitle("Add new user");
            addScreenStage.initModality(Modality.WINDOW_MODAL);
            addScreenStage.initOwner(parent);
            NewScreenController controller = loader.getController();
            controller.setNewScreenStage(addScreenStage);
            Scene scene = new Scene(layout);
            addScreenStage.setScene(scene);
            addScreenStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditScreenForm(Stage parent, Screen screen){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/editScreenView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            GridPane layout = loader.load();

            Stage editScreenStage = new Stage();
            editScreenStage.setTitle("Edit user");
            editScreenStage.initModality(Modality.WINDOW_MODAL);
            editScreenStage.initOwner(parent);

            EditScreenController presenter = loader.getController();
            presenter.setStage(editScreenStage);
            presenter.setScreen(screen);

            Scene scene = new Scene(layout);
            editScreenStage.setScene(scene);
            editScreenStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showShowingManagement(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/manageShowingsView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage showingManagementStage = new Stage();
            showingManagementStage.setTitle("Manage users");
            showingManagementStage.initModality(Modality.WINDOW_MODAL);
            showingManagementStage.initOwner(parent);

            ShowingManagementPresenter presenter = loader.getController();
            presenter.setShowingManagementStage(showingManagementStage);
            Scene scene = new Scene(layout);
            showingManagementStage.setScene(scene);
            showingManagementStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewShowingForm(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/newShowingForm.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage addShowingStage = new Stage();
            addShowingStage.setTitle("Add new user");
            addShowingStage.initModality(Modality.WINDOW_MODAL);
            addShowingStage.initOwner(parent);
            NewShowingController controller = loader.getController();
            controller.setNewShowingStage(addShowingStage);
            Scene scene = new Scene(layout);
            addShowingStage.setScene(scene);
            addShowingStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditShowingForm(Stage parent, Showing showing){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/editShowingForm.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage editShowingStage = new Stage();
            editShowingStage.setTitle("Edit showing");
            editShowingStage.initModality(Modality.WINDOW_MODAL);
            editShowingStage.initOwner(parent);

            EditShowingController presenter = loader.getController();
            presenter.setEditShowingStage(editShowingStage);
            presenter.setShowing(showing);

            Scene scene = new Scene(layout);
            editShowingStage.setScene(scene);
            editShowingStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTicketPurchaseView(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/ticketPurchaseView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage buyTicketStage = new Stage();
            buyTicketStage.setTitle("Puchase ticket");
            buyTicketStage.initModality(Modality.WINDOW_MODAL);
            buyTicketStage.initOwner(parent);

            TicketPurchasePresenter presenter = loader.getController();
            presenter.setTicketPurchaseStage(buyTicketStage);

            Scene scene = new Scene(layout);
            buyTicketStage.setScene(scene);
            buyTicketStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTicketClippingView(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/ticketClipView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage clipTicketStage = new Stage();
            clipTicketStage.setTitle("Clip ticket");
            clipTicketStage.initModality(Modality.WINDOW_MODAL);
            clipTicketStage.initOwner(parent);

            TicketClippingPresenter presenter = loader.getController();
            presenter.setStage(clipTicketStage);

            Scene scene = new Scene(layout);
            clipTicketStage.setScene(scene);
            clipTicketStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void setUserId(Long id){this.userId = id;}

    public Long getUserId(){return this.userId;}


    public void logOut(){
        this.primaryStage.close();
        this.userRole = null;
        initRootLayout();
    }

    public void showStatistics(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/statisticsView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage showStatisticsStage = new Stage();
            showStatisticsStage.setTitle("Satistics");
            showStatisticsStage.initModality(Modality.NONE);
            showStatisticsStage.initOwner(parent);
            ShowStatisticsController controller = loader.getController();
            controller.setShowStatisticsStage(showStatisticsStage);
            Scene scene = new Scene(layout);
            showStatisticsStage.setScene(scene);
            showStatisticsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNotification(Stage parent){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/notificationView.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            BorderPane layout = loader.load();

            Stage notificationStage = new Stage();
            notificationStage.setTitle("Send notification");
            notificationStage.initModality(Modality.WINDOW_MODAL);
            notificationStage.initOwner(parent);
            NotificationController controller = loader.getController();
            controller.setNotificationStage(notificationStage);
            Scene scene = new Scene(layout);
            notificationStage.setScene(scene);
            notificationStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
