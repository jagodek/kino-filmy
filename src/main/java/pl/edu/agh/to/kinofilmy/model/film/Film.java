package pl.edu.agh.to.kinofilmy.model.film;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Film represents each movie with title, duration, director and other basic movie info
 */

@Entity
public class Film {

    private Long id;
    private String title;
    private LocalTime runtime;
    private String genre;
    private String director;

    @Lob
    @Column(length = 10485760)
    private byte[] icon;

    public Film() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Film(String title, LocalTime runtime, String genre, String director, byte[] icon) {
        this.title = title;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.icon = icon;
    }

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

    @Lob
    @Column(length = 10485760)
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

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", runtime=" + runtime +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", icon=" + Arrays.toString(icon) +
                '}';
    }
}
