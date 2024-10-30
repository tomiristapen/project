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
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }

    public void removeSeat(Seat seat) {
        this.seats.remove(seat);
    }

    public long getAvailableSeatsCount() {
        return seats.stream().filter(Seat::isAvailable).count();
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
