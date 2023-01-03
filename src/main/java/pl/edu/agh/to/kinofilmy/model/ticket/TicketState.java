package pl.edu.agh.to.kinofilmy.model.ticket;

public enum TicketState {
    Sold ("sold"),
    Clipped ("clipped");

    private String state;

    TicketState(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
