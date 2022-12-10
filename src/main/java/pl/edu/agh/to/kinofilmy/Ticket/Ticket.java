package pl.edu.agh.to.kinofilmy.Ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Ticket {
    @Id
    private Long id;

    private Long seanceId;
    private Long clientId;
    private float price;
    private LocalDate saleDate;
    private int row;
    private int seat;
    private String state;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeanceId(Long seanceId) {
        this.seanceId = seanceId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public Long getSeanceId() {
        return seanceId;
    }

    public Long getClientId() {
        return clientId;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public String getState() {
        return state;
    }
}
