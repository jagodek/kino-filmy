package pl.edu.agh.to.kinofilmy.Film;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class Film {
    private int id;
    private String title;
    private LocalTime runtime;
    private String genre;
    private String director;
    private byte[] icon;

    public Film() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Film(String title, LocalTime runtime, String genre, String director, byte[] icon) {
        this.title = title;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.icon = icon;
    }

//    public int getId() {
//        return id;
//    }

    public String getTitle() {
        return title;
    }

    public LocalTime getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRuntime(LocalTime runtime) {
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }
}
