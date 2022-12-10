package pl.edu.agh.to.kinofilmy.Ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long seanceId;
    private Long clientId;
    private float price;
    private Date saleDate;
    private int seatRow;
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

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setState(String state) {
        this.state = state;
    }


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

    public int getSeat() {
        return seat;
    }

    public String getState() {
        return state;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }
}
