package pl.edu.agh.to.kinofilmy.model.film;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FilmStatisticTickets {
    private LongProperty id;
    private StringProperty title;
    private LongProperty ticketsSold;

    public FilmStatisticTickets(Long id, String title, Long ticketsSold) {
        this.id = new SimpleLongProperty(id);
        this.title = new SimpleStringProperty( title);
        this.ticketsSold = new SimpleLongProperty( ticketsSold);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public long getTicketsSold() {
        return ticketsSold.get();
    }

    public LongProperty ticketsSoldProperty() {
        return ticketsSold;
    }

    public void setTicketsSold(long ticketsSold) {
        this.ticketsSold.set(ticketsSold);
    }
}
