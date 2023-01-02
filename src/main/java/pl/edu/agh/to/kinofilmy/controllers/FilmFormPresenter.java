package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.Film;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class FilmFormPresenter {

    private Stage filmFormStage;

    private boolean approved;

    private Film film;

    @FXML
    private TextField filmIdField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField runtimeField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField directorField;

    @FXML
    private Label selectedFileText;

    @FXML
    private ImageView filmPosterDisplay;

    public void setFilmFormStage(Stage filmFormStage) {
        this.filmFormStage = filmFormStage;
    }

    public void setFilm(Film film, boolean newFilm) {
        this.film = film;
        if(!newFilm) updateDataValues();
    }

    public boolean isApproved() {
        return approved;
    }

    @FXML
    public void handleSelectFileAction(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select film poster image");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.setSelectedExtensionFilter(extensionFilter);
        File filmImageFile = fileChooser.showOpenDialog(filmFormStage);
        byte[] filmImageByteArray = new byte[(int) filmImageFile.length()];
        try (FileInputStream fis = new FileInputStream(filmImageFile)){
            fis.read(filmImageByteArray);
            selectedFileText.textProperty().setValue(filmImageFile.getAbsolutePath());
            film.setIcon(filmImageByteArray);
            Image filmImage = new Image(new ByteArrayInputStream(filmImageByteArray));
            filmPosterDisplay.setImage(filmImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleOKAction(ActionEvent event){
        updateModel();
        approved = true;
        filmFormStage.close();
    }

    private void updateModel() {
        film.setTitle(titleField.getText());
        film.setRuntime(LocalTime.parse(runtimeField.getText()));
        film.setGenre(genreField.getText());
        film.setDirector(directorField.getText());
    }

    private void updateDataValues(){
        if(film != null) {
            filmIdField.textProperty().setValue(film.getId().toString());
            titleField.textProperty().setValue(film.getTitle());
            runtimeField.textProperty().setValue(film.getRuntime().format(DateTimeFormatter.ofPattern("HH:mm")));
            genreField.textProperty().setValue(film.getGenre());
            directorField.textProperty().setValue(film.getDirector());
            Image filmIcon = new Image(new ByteArrayInputStream(film.getIcon()));
            filmPosterDisplay.imageProperty().setValue(filmIcon);
        }
    }
}
