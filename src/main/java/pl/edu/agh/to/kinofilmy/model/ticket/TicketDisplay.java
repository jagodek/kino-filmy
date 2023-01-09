package pl.edu.agh.to.kinofilmy.model.ticket;

import javafx.beans.property.*;
import pl.edu.agh.to.kinofilmy.model.showing.Showing;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

public class TicketDisplay {
    private LongProperty id;
    private ObjectProperty<Showing> showing;
    private LongProperty clientId;
    private ObjectProperty<Float> price;
    private ObjectProperty<Date> saleDate;
    private ObjectProperty<Integer> seatRow;
    private ObjectProperty<Integer> seat;
    private StringProperty state;

    public TicketDisplay(Ticket ticket) {
        this.showing = new SimpleObjectProperty<>(ticket.getShowing());
        this.clientId = new SimpleLongProperty(ticket.getClientId());
        this.price = new SimpleObjectProperty<>(ticket.getPrice());
        this.saleDate = new SimpleObjectProperty<>(ticket.getSaleDate());
        this.seatRow = new SimpleObjectProperty<>(ticket.getSeatRow());
        this.seat = new SimpleObjectProperty<>(ticket.getSeat());
        this.state = new SimpleStringProperty(ticket.getState());
    }

    public Long getId() {
        return id.get();
    }

    public Showing getShowing() {
        return showing.get();
    }

    public Long getClientId() {
        return clientId.get();
    }

    public float getPrice() {
        return price.get();
    }

    public Date getSaleDate() {
        return saleDate.get();
    }

    public int getSeatRow() {
        return seatRow.get();
    }

    public int getSeat() {
        return seat.get();
    }

    public String getState() {
        return state.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public ObjectProperty<Showing> showingProperty() {
        return showing;
    }

    public LongProperty clientIdProperty() {
        return clientId;
    }

    public ObjectProperty<Float> priceProperty() {
        return price;
    }

    public ObjectProperty<Date> saleDateProperty() {
        return saleDate;
    }

    public ObjectProperty<Integer> seatRowProperty() {
        return seatRow;
    }

    public ObjectProperty<Integer> seatProperty() {
        return seat;
    }

    public StringProperty stateProperty() {
        return state;
    }
}
