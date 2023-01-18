package pl.edu.agh.to.kinofilmy.model.film;

import javafx.beans.property.LongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;
import java.util.*;


@Service
@Transactional
public class FilmService {

    private final FilmRepository repository;

    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }


    public List<Film> findAll(){
        return this.repository.findAll();
    }

    public ObservableList<FilmDisplay> findAllAsFilmDisplay(){
        ObservableList<FilmDisplay> filmDisplayList = FXCollections.observableArrayList();
        for(Film f: this.repository.findAll()){
            filmDisplayList.add(new FilmDisplay(f));
        }
        return filmDisplayList;
    }

    public void save(Film film) {
        this.repository.save(film);
    }

    public void delete(Film film){
        this.repository.delete(film);
    }

    public Film filmDisplayToFilm(FilmDisplay filmDisplay){
        try{
            Film film = new Film(filmDisplay);
            film.setIcon(repository.getReferenceById(filmDisplay.getId()).getIcon());
            return film;
        } catch (EntityNotFoundException e) {
            return new Film(filmDisplay);
        }
    }

    public Image getFilmImageById(long id){
        Film film = repository.getReferenceById(id);
        byte[] byteImg = film.getIcon();
        return new Image(new ByteArrayInputStream(byteImg));
    }

    public boolean exists(long id){
        if(this.repository.existsFilmById(id)){
            return true;
        }
        return false;
    }

    public Optional<Film> getFilmById(long id){
        return this.repository.findById(id);
    }


    public ObservableList<FilmStatisticTickets> getMoviesByTicketsSold(String dateRange){
        Date date = new Date();
        List<Object[]> l = this.repository.findFilmsByTicketsSoldOnYear(date);
        if(dateRange == "This Month") {
            l = this.repository.findFilmsByTicketsSoldOnMonth(date);
        }
        if(dateRange == "This Day"){
            l = this.repository.findFilmsByTicketsSoldOnDate(date);
        }
        ObservableList<FilmStatisticTickets> list = FXCollections.observableArrayList();
        int c = 1;
        for (Object[] pair:l) {
            Film film = (Film) pair[0];
            Long num = (Long) pair[1];
            list.add(new FilmStatisticTickets(c,film.getId(),film.getTitle(),num));
            c++;
        }

        return list;
    }





    public ObservableList<FilmStatisticTickets> getMoviesByDirectorDay(String dateRange){
        Date date = new Date();

        ObservableList<FilmStatisticTickets> list = FXCollections.observableArrayList();
        int c = 1;
        List<Object[]> l = this.repository.findFilmsByTicketsSoldOnYear(date);
        if(dateRange == "This Month") {
            l = this.repository.findFilmsByTicketsSoldOnMonth(date);
        }
        if(dateRange == "This Day"){
            l = this.repository.findFilmsByTicketsSoldOnDate(date);
        }

        for (Object[] pair:l) {
            Film film = (Film) pair[0];
            Long num = (Long) pair[1];
            boolean edited = false;
            for(int i =0;i<list.size();i++){
                if (list.get(i).getString() == film.getDirector()){
                    list.get(i).setTicketsSold(list.get(i).getTicketsSold() + num);
                    edited = true;
                    break;
                }
            }
            if(!edited){
                list.add(new FilmStatisticTickets(0,film.getId(),film.getDirector(),num));
            }
        }
        Collections.sort(list);
        for(int i =0;i<list.size();i++){
            list.get(i).setPlace(c);
            c++;
        }
        return list;
    }


    public ObservableList<FilmStatisticTickets> getMoviesByGenre(String dateRange){
        Date date = new Date();

        ObservableList<FilmStatisticTickets> list = FXCollections.observableArrayList();
        int c = 1;
        List<Object[]> l = this.repository.findFilmsByTicketsSoldOnYear(date);
        if(dateRange == "This Month") {
            l = this.repository.findFilmsByTicketsSoldOnMonth(date);
        }
        if(dateRange == "This Day"){
            l = this.repository.findFilmsByTicketsSoldOnDate(date);
        }

        for (Object[] pair:l) {
            Film film = (Film) pair[0];
            Long num = (Long) pair[1];
            boolean edited = false;
            for(int i =0;i<list.size();i++){
                if (list.get(i).getString() == film.getGenre()){
                    list.get(i).setTicketsSold(list.get(i).getTicketsSold() + num);
                    edited = true;
                    break;
                }
            }
            if(!edited){
                list.add(new FilmStatisticTickets(0,film.getId(),film.getGenre(),num));
            }
        }
        Collections.sort(list);
        for(int i =0;i<list.size();i++){
            list.get(i).setPlace(c);
            c++;
        }
        return list;
    }




}
