package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.Film;
import pl.edu.agh.to.kinofilmy.model.film.FilmService;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class FilmFormPresenter {

    private FilmService filmService;
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


    @FXML
    private Label filmIdError;

    @FXML
    private Label titleError;

    @FXML
    private Label genreError;

    @FXML
    private Label directorError;

    @FXML
    private Button submit;

    public FilmFormPresenter(FilmService filmService){
        this.filmService = filmService;
    }

    public void setFilmFormStage(Stage filmFormStage) {
        this.filmFormStage = filmFormStage;
    }

    public void setFilm(Film film, boolean newFilm) {
        this.film = film;
        if(!newFilm) updateDataValues();
    }

    @FXML
    public void initialize(){
        submit.setDisable(true);

        AtomicBoolean filmIdTouched = new AtomicBoolean(false);
        this.filmIdField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!filmIdTouched.get()){
                filmIdTouched.set(true);
                this.filmIdError.setText("Film id required");
            }
        });
        this.filmIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(filmIdTouched.get()) {
                Pair<Boolean, String> err = isFilmIdValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if (!valid) {
                    this.filmIdError.setText(msg);
                } else {
                    this.filmIdError.setText("");
                }
            }
            setButton();
        });


        AtomicBoolean titleTouched = new AtomicBoolean(false);
        this.titleField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!titleTouched.get()){
                titleTouched.set(true);
                this.titleError.setText("Title required");
            }
        });
        this.titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(titleTouched.get()) {
                Pair<Boolean, String> err = required(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if (!valid) {
                    this.titleError.setText(msg);
                } else {
                    this.titleError.setText("");
                }
            }
            setButton();
        });

        AtomicBoolean directorTouched = new AtomicBoolean(false);
        this.directorField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!directorTouched.get()){
                directorTouched.set(true);
                this.directorError.setText("Director required");
            }
        });
        this.directorField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(directorTouched.get()) {
                Pair<Boolean, String> err = required(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if (!valid) {
                    this.directorError.setText(msg);
                } else {
                    this.directorError.setText("");
                }
            }
            setButton();
        });

        AtomicBoolean genreTouched = new AtomicBoolean(false);
        this.genreField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!genreTouched.get()){
                genreTouched.set(true);
                this.genreError.setText("Genre required");
            }
        });
        this.genreField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(genreTouched.get()) {
                Pair<Boolean, String> err = required(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if (!valid) {
                    this.genreError.setText(msg);
                } else {
                    this.genreError.setText("");
                }
            }
            setButton();
        });


    }

    private Pair<Boolean,String> isFilmIdValid(String string){
        if(string.equals("")){
            return new Pair<>(false,"name required");
        }
        else if(!string.matches("[0-9]*"))
        {
            return  new Pair<>(false,"Wrong id format");
        }
//        else if(this.filmService.exists(Long.parseLong(string))){
//            return  new Pair<>(false,"Id already esists");
//        }
        return new Pair<>(true,"");
    }

    private Pair<Boolean,String> required(String string){
        if(string.equals("")){
            return new Pair<>(false,"required");
        }
        return new Pair<>(true,"");
    }



    private void setButton(){
        if(isFilmIdValid(this.filmIdField.getText()).getKey() &&
                required(this.titleField.getText()).getKey() &&
                required(this.genreField.getText()).getKey() &&
                required(this.directorField.getText()).getKey()
        ){
            this.submit.setDisable(false);
        }
        else
            this.submit.setDisable(true);
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
