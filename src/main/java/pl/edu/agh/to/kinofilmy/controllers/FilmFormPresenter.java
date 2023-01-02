package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.film.Film;

@Controller
public class FilmFormPresenter {

    private Stage filmFormStage;

    private boolean approved;

    private Film film;

    public void setFilmFormStage(Stage filmFormStage) {
        this.filmFormStage = filmFormStage;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public boolean isApproved() {
        return approved;
    }

    @FXML
    public void handleSelectFileAction(ActionEvent event){

    }

    @FXML
    public void handleOKAction(ActionEvent event){

    }
}
