package MVC.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private Movie movie;
    private LocalDateTime showTime;
    private List<Seat> seats;

    public Show(Movie movie, LocalDateTime showTime) {
        this.movie = movie;
        this.showTime = showTime;
        this.seats = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= 10; j++) {
                seats.add(new Seat(i + 1, j));
            }
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    @Override
    public String toString() {
        return "Show{" +
                "movie: " + movie.getTitle() +
                ", showTime: " + showTime +
                ", seats: " + seats +
                '}';
    }
}

