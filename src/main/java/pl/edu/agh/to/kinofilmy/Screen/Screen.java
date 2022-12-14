package pl.edu.agh.to.kinofilmy.Screen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  Screen represents each room where the movies are played, mainly capacity and name
 *
 */


@Entity
public class Screen {
    @Id
    private Long id;
    private String name;
    private int seatsNumber;
    private int rowNumber;


    public Screen(String name, int seatsNumber, int rowNumber) {
        this.name = name;
        this.seatsNumber = seatsNumber;
        this.rowNumber = rowNumber;
    }

    public Screen() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seatsNumber=" + seatsNumber +
                ", rowNumber=" + rowNumber +
                '}';
    }
}
