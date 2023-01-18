package pl.edu.agh.to.kinofilmy.controllers;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;
import pl.edu.agh.to.kinofilmy.model.screen.Seat;

import java.util.Set;


public class SelectSeatEventHandler implements EventHandler<MouseEvent> {
    private final Pane seatDisplay;
    private final Set<Seat> selectedSeats;
    private final Seat seat;
    private final boolean state;
    private boolean selected = false;

    private final Background freeBackground = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
    private final Background selectedBackground = new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY));

    public SelectSeatEventHandler(Pane seatDisplay, Set<Seat> selectedSeats, Screen screen, int row, int seatInRow, boolean state) {
        this.seatDisplay = seatDisplay;
        this.selectedSeats = selectedSeats;
        this.seat = new Seat(screen, row, seatInRow);
        this.state = state;
    }

    public SelectSeatEventHandler(Pane seatDisplay, Set<Seat> selectedSeats, Seat seat, boolean state) {
        this.seatDisplay = seatDisplay;
        this.selectedSeats = selectedSeats;
        this.seat = seat;
        this.state = state;
    }

    @Override
    public void handle(MouseEvent event) {
        if(state){
            if(selected){
                this.seatDisplay.setBackground(freeBackground);
                this.selectedSeats.remove(this.seat);
                this.selected = false;
            }
            else {
                this.seatDisplay.setBackground(selectedBackground);
                this.selectedSeats.add(this.seat);
                this.selected = true;
            }

        }
    }
}
