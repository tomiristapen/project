package MVC.model;

import java.util.List;
import java.util.Optional;
public class BookingService {
    private List<Movie> movies;

    public BookingService(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getAvailableMovies() {
        return movies;
    }

    public boolean bookSeat(Show show, Seat seat) {
        Optional<Seat> matchedSeat = show.getSeats().stream()
                .filter(s -> s.getRow() == seat.getRow() && s.getNumber() == seat.getNumber() && s.isAvailable())
                .findFirst();

        if (matchedSeat.isPresent()) {
            matchedSeat.get().setAvailable(false);
            return true;
        }
        return false;
    }
}


