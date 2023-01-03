package pl.edu.agh.to.kinofilmy.model.screen;

import pl.edu.agh.to.kinofilmy.model.screen.Screen;

public class Seat {
    private final Screen screen;
    private final int rowNumber;
    private final int seatNumber;

    public Seat(Screen screen, int rowNumber, int seatNumber) {
        this.screen = screen;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
