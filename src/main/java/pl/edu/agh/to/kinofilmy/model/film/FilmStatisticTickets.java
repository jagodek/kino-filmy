package pl.edu.agh.to.kinofilmy.model.film;

import javafx.beans.property.*;

public class FilmStatisticTickets implements Comparable<FilmStatisticTickets> {
    private LongProperty id;
    private IntegerProperty place;
    private StringProperty string;
    private LongProperty ticketsSold;

    public FilmStatisticTickets(int place,Long id, String string, Long ticketsSold) {
        this.place = new SimpleIntegerProperty(place);
        this.id = new SimpleLongProperty(id);
        this.string = new SimpleStringProperty( string);
        this.ticketsSold = new SimpleLongProperty( ticketsSold);
    }

    public long getId() {
        return id.get();
    }

    public int getPlace() {
        return place.get();
    }

    public IntegerProperty placeProperty() {
        return place;
    }

    public void setPlace(int place) {
        this.place.set(place);
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getString() {
        return string.get();
    }

    public StringProperty stringString() {
        return string;
    }

    public void setString(String string) {
        this.string.set(string);
    }

    public Long getTicketsSold() {
        return ticketsSold.get();
    }

    public LongProperty ticketsSoldProperty() {
        return ticketsSold;
    }

    public void setTicketsSold(long ticketsSold) {
        this.ticketsSold.set(ticketsSold);
    }


    @Override
    public int compareTo(FilmStatisticTickets f){
        if(getTicketsSold() == null || f.getTicketsSold() == null){
            return 0;
        }
        return getTicketsSold().compareTo(f.getTicketsSold());
    }

}
