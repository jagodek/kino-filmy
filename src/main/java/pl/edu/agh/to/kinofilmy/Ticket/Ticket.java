package pl.edu.agh.to.kinofilmy.Ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Ticket {

    private Long id;

    private Long seanceId;
    private Long clientId;
    private float price;
    private Date saleDate;
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

    public void setSaleDate(Date saleDate) {
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

    public Date getSaleDate() {
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
