package pl.edu.agh.to.kinofilmy.controllers.manageShowingControllers;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;
import pl.edu.agh.to.kinofilmy.model.showing.ShowingDisplay;
import pl.edu.agh.to.kinofilmy.model.showing.ShowingService;

import java.util.Date;

@Controller
public class ShowingManagementPresenter {
    private Stage showingManagementStage;

    private  final KinoFilmyApplicationController applicationController;
    private final ShowingService showingService;

    @FXML
    private TableView<ShowingDisplay> showingsTable;
    @FXML
    private TableColumn<ShowingDisplay, String> filmColumn;
    @FXML
    private  TableColumn<ShowingDisplay, String> screenColumn;
    @FXML
    private TableColumn<ShowingDisplay, Date> dateColumn;
    @FXML
    private Button addShowingButton ;
    @FXML
    private  Button detailsButton;
    @FXML
    private  Button deleteButton;

    public ShowingManagementPresenter(KinoFilmyApplicationController applicationController, ShowingService showingService) {
        this.applicationController = applicationController;
        this.showingService = showingService;
    }

    public void setShowingManagementStage(Stage showingManagementStage) {
        this.showingManagementStage = showingManagementStage;
    }

    @FXML
    public void initialize(){
        this.showingsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.filmColumn.setCellValueFactory(val -> val.getValue().filmTitleProperty());
        this.screenColumn.setCellValueFactory(val -> val.getValue().screenNameProperty());
        this.dateColumn.setCellValueFactory(val -> val.getValue().dateProperty());

        deleteButton.disableProperty().bind(Bindings.isEmpty(showingsTable.getSelectionModel().getSelectedItems()));
        detailsButton.disableProperty().bind(Bindings.isEmpty(showingsTable.getSelectionModel().getSelectedItems()));
    }
    @FXML
    private void handleAddShowingAction(){
        applicationController.showNewShowingForm(showingManagementStage);
        refresh();
    }

    @FXML
    private void handleEditShowingAction(){
        applicationController.showEditShowingForm(
                showingManagementStage,
                showingService.showingDisplayToShowing(this.showingsTable.getSelectionModel().getSelectedItem()));
        refresh();
    }

    @FXML
    private void handleDeleteShowingAction(){
        Showing showing = showingService.showingDisplayToShowing(this.showingsTable.getSelectionModel().getSelectedItem());
        showingService.delete(showing);
        refresh();
    }

    private void refresh(){
        showingsTable.getSelectionModel().clearSelection();
        ObservableList<ShowingDisplay> screensList = showingService.findAllShowingDisplay();
        showingsTable.setItems(screensList);
    }
}
