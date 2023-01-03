package pl.edu.agh.to.kinofilmy.controllers.managesScreenControllers;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenDisplay;
import pl.edu.agh.to.kinofilmy.model.screen.ScreenService;

@Controller
public class ScreenManagementPresenter {
    private Stage screenManagementStage;
    private final KinoFilmyApplicationController applicationController;
    private final ScreenService screenService;
    @FXML
    private TableView<ScreenDisplay> screensTable;
    @FXML
    private TableColumn<ScreenDisplay, String> nameColumn;
    @FXML
    private TableColumn<ScreenDisplay, Integer> seatsNumberColumn;
    @FXML
    private TableColumn<ScreenDisplay, Integer> rowNumberColumn;
    @FXML
    private Button addScreenButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button deleteButton;

    public ScreenManagementPresenter(KinoFilmyApplicationController applicationController, ScreenService screenService) {
        this.applicationController = applicationController;
        this.screenService = screenService;
    }

    public void setScreenManagementStage(Stage stage) {
        this.screenManagementStage =stage;
    }

    @FXML
    private void initialize(){
        this.screensTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.nameColumn.setCellValueFactory(val -> val.getValue().getNameProperty());
        this.seatsNumberColumn.setCellValueFactory(val -> val.getValue().getSeatsNumberProperty());
        this.rowNumberColumn.setCellValueFactory(val -> val.getValue().getRowNumberProperty());

        deleteButton.disableProperty().bind(Bindings.isEmpty(screensTable.getSelectionModel().getSelectedItems()));
        detailsButton.disableProperty().bind(Bindings.isEmpty(screensTable.getSelectionModel().getSelectedItems()));
        refresh();
    }

    @FXML
    private void handleAddScreenAction(){
        applicationController.showNewScreenForm(screenManagementStage);
        refresh();
    }

    @FXML
    private void handleEditScreenAction(){
        applicationController.showEditScreenForm(
                screenManagementStage,
                screenService.screenDisplayToScreen(this.screensTable.getSelectionModel().getSelectedItem()));
        refresh();
    }

    @FXML
    private void handleDeleteScreenAction(){
        Screen screen = screenService.screenDisplayToScreen(this.screensTable.getSelectionModel().getSelectedItem());
        screenService.deleteScreen(screen);
        refresh();
    }

    private void refresh(){
        screensTable.getSelectionModel().clearSelection();
        ObservableList<ScreenDisplay> screensList = screenService.findAllAsScreenDisplay();
        screensTable.setItems(screensList);
    }
}
