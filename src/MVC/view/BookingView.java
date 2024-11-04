package MVC.view;

import Decorator.TicketAdd;
import MVC.model.Movie;
import MVC.model.Seat;

import java.util.List;

public class BookingView {
    public void displayMovies(List<Movie> movies) {
        System.out.println("Доступные фильмы:");
        movies.forEach(movie ->
                System.out.println("Фильм: " + movie.getTitle() + "  Жанр: " + movie.getGenre())
        );
    }


    public void displaySeatAvailability(List<Seat> seats) {
        System.out.println("Список мест:");
        seats.forEach(seat ->
                System.out.println("Место: Ряд " + seat.getRow() + ", Номер " + seat.getNumber() + " - " + (seat.isAvailable() ? "Доступно" : "Забронировано"))
        );
    }

    public void displayTicketInfo(TicketAdd ticket) {
        System.out.println("Информация о билете: " + ticket.getDescription());
        System.out.println("Стоимость: $" + ticket.getCost());
    }

    public void displayBookingResult(boolean isBooked) {
        if (isBooked) {
            System.out.println("Место успешно забронировано!");
        } else {
            System.out.println("Ошибка! Место уже забронировано.");
        }
    }
}
