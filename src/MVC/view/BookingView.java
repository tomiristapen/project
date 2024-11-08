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
    }


    public void displayBookingResult(boolean success, double finalPrice) {
        if (success) {
            System.out.println("Бронирование успешно! Итоговая цена: " + finalPrice);
        } else {
            System.out.println("Это место уже занято.");
        }
    }

    public void displayPaymentResult(String message) {
        System.out.println(message);
    }
}
