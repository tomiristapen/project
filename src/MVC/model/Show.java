package MVC.model;
import java.util.List;

public class Show {
    private List<Seat> seats;
    private String time;

    public Show(List<Seat> seats, String time) {
        this.seats = seats;
        this.time = time;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getTime() {
        return time;
    }
}
