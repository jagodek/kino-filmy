package pl.edu.agh.to.kinofilmy.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.email.EmailService;
import pl.edu.agh.to.kinofilmy.model.film.Film;
import pl.edu.agh.to.kinofilmy.model.film.FilmDisplay;
import pl.edu.agh.to.kinofilmy.model.film.FilmService;

import java.time.LocalTime;

@Controller
public class FilmManagementController {

    final FilmService filmService;
    final EmailService emailService;

    private Stage filmManagementStage;

    private ObservableList<FilmDisplay> filmList;

    final KinoFilmyApplicationController applicationController;

    @FXML
    private Button newFilmButton;

    @FXML
    private Button editFilmButton;

    @FXML
    private Button deleteFilmButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button addToRecommendedButton;
    @FXML
    private Button removeFromRecommendedButton;
    @FXML
    private ImageView filmImageDisplay;

    @FXML
    private TableView<FilmDisplay> filmsTable;

    @FXML
    private TableColumn<FilmDisplay, Long> idColumn;

    @FXML
    private TableColumn<FilmDisplay, String> titleColumn;

    @FXML
    private TableColumn<FilmDisplay, LocalTime> runtimeColumn;

    @FXML
    private TableColumn<FilmDisplay, String> genreColumn;

    @FXML
    private TableColumn<FilmDisplay, String> directorColumn;

    @FXML
    private TableColumn<FilmDisplay, String> isRecommendedColumn;

    public FilmManagementController(FilmService filmService, EmailService emailService, KinoFilmyApplicationController applicationController) {
        this.filmService = filmService;
        this.emailService = emailService;
        this.applicationController = applicationController;
    }

    private void refreshFilmData(){
        filmsTable.getSelectionModel().clearSelection();
        this.filmList = filmService.findAllAsFilmDisplay();
        filmsTable.setItems(filmList);
    }

    @FXML
    public void initialize(){
        filmsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        filmsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                displayFilmImage(newSelection);
            } else {
                filmImageDisplay.setImage(null);
            }
        });

        idColumn.setCellValueFactory(filmValue -> filmValue.getValue().idProperty().asObject());
        titleColumn.setCellValueFactory(filmValue -> filmValue.getValue().titleProperty());
        runtimeColumn.setCellValueFactory(filmValue -> filmValue.getValue().runtimeProperty());
        genreColumn.setCellValueFactory(filmValue -> filmValue.getValue().genreProperty());
        directorColumn.setCellValueFactory(filmValue -> filmValue.getValue().directorProperty());
        isRecommendedColumn.setCellValueFactory(filmValue -> filmValue.getValue().isRecommendedProperty().asString());

        deleteFilmButton.disableProperty().bind(Bindings.isEmpty(filmsTable.getSelectionModel().getSelectedItems()));
        editFilmButton.disableProperty().bind(Bindings.isEmpty(filmsTable.getSelectionModel().getSelectedItems()));

        refreshFilmData();
    }



    public void setFilmManagementStage(Stage filmManagementStage) {
        this.filmManagementStage = filmManagementStage;
    }

    private void displayFilmImage(FilmDisplay filmDisplay){
        Image filmImage = filmService.getFilmImageById(filmDisplay.getId());
        filmImageDisplay.setImage(filmImage);
    }

    @FXML
    public void handleRefreshAction(ActionEvent event){
        filmsTable.setItems(FXCollections.emptyObservableList());
        refreshFilmData();
    }

    @FXML
    public void handleNewFilmAction(ActionEvent event){
        Film film = new Film();
        if(applicationController.showFilmForm(filmManagementStage, film, true)) filmService.save(film);
        refreshFilmData();
    }

    @FXML
    public void handleEditFilmAction(ActionEvent event){
        Film film = filmService.filmDisplayToFilm(filmsTable.getSelectionModel().getSelectedItem());
        if(applicationController.showFilmForm(filmManagementStage, film, false)) filmService.save(film);
        refreshFilmData();
    }

    @FXML
    public void handleDeleteFilmAction(ActionEvent event){
        Film film = filmService.filmDisplayToFilm(filmsTable.getSelectionModel().getSelectedItem());
        filmService.delete(film);
        refreshFilmData();
    }

    @FXML
    public void handleAddToRecommended(ActionEvent event){
        Film film = filmService.filmDisplayToFilm(filmsTable.getSelectionModel().getSelectedItem());
        if(!film.isRecommended()){
            film.setRecommended(true);
            filmService.save(film);
            this.emailService.sendNotificationToAllEmployees("Recommended film",
                    "Film " + film.getTitle() + " should be recommended to customers");
        }
    }

    @FXML
    public void handleRemoveFromRecommended(ActionEvent event){
        Film film = filmService.filmDisplayToFilm(filmsTable.getSelectionModel().getSelectedItem());
        if(film.isRecommended()){
            film.setRecommended(false);
            filmService.save(film);
            this.emailService.sendNotificationToAllEmployees("Recommended film",
                    "Film " + film.getTitle() + " should no longer be recommended to customers");
        }
    }

    public Stage getFilmManagementStage() {
        return filmManagementStage;
    }
}
