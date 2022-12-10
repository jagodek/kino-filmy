package pl.edu.agh.to.kinofilmy.Ticket;

import pl.edu.agh.to.kinofilmy.Seance.Seance;

import javax.persistence.*;
import java.util.Date;

/**
 * Ticket is the class that represents ticket that customers buy for certain movie.
 * It stores information about which showing customer is allowed to watch with this ticket,
 * where will he/she should seat (row and seat number),
 * price of the ticket, when this ticket has been sold,
 * to which customer it belongs,
 * and in which state it is (sold, returned, etc.)
 */
@Entity
public class Ticket {
    private Long id;
    private Seance seance;
    private Long clientId;
    private float price;
    private Date saleDate;
    private int seatRow;
    private int seat;
    private String state;

    public Ticket(Seance seance, Long clientId, float price, Date saleDate, int seatRow, int seat, String state) {
        this.seance = seance;
        this.clientId = clientId;
        this.price = price;
        this.saleDate = saleDate;
        this.seatRow = seatRow;
        this.seat = seat;
        this.state = state;
    }

    public Ticket() {    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @ManyToOne
    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
