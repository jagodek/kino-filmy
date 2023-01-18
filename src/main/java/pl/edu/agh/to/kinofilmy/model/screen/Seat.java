package pl.edu.agh.to.kinofilmy.model.screen;

import pl.edu.agh.to.kinofilmy.model.screen.Screen;

import java.util.Objects;

public class Seat implements Comparable<Seat>{
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

    @Override
    public int hashCode() {
        return Objects.hash(screen, rowNumber, seatNumber);
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(!(other instanceof Seat that)) return false;
        return this.screen.equals(that.screen) && this.rowNumber == that.rowNumber && this.seatNumber == that.seatNumber;
    }

    @Override
    public int compareTo(Seat other) {
        if(other == null) throw new NullPointerException();
        if(this.screen != other.screen) throw new ClassCastException();
        if(this.equals(other)) return 0;
        if(this.rowNumber < other.rowNumber) return -1;
        if(this.rowNumber > other.rowNumber) return 1;
        return Integer.compare(this.seatNumber, other.seatNumber);
    }


}
