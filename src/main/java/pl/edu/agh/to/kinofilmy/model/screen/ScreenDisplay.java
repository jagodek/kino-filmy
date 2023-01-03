package pl.edu.agh.to.kinofilmy.model.screen;

import javafx.beans.property.*;

public class ScreenDisplay {
    private final LongProperty id;
    private final StringProperty name;
    private final ObjectProperty<Integer> seatsNumber;
    private final ObjectProperty<Integer> rowNumber;


    public ScreenDisplay(Screen screen) {
        this.id = new SimpleLongProperty(screen.getId());
        this.name = new SimpleStringProperty(screen.getName());
        this.seatsNumber = new SimpleObjectProperty<>(screen.getSeatsNumber());
        this.rowNumber = new SimpleObjectProperty<>(screen.getRowNumber());
    }

    public Long getId() {
        return id.get();
    }

    public LongProperty getIdProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public int getSeatsNumber() {
        return seatsNumber.get();
    }

    public ObjectProperty<Integer> getSeatsNumberProperty() {
        return seatsNumber;
    }

    public int getRowNumber() {
        return rowNumber.get();
    }

    public ObjectProperty<Integer> getRowNumberProperty() {
        return rowNumber;
    }
}
