package MVC.controller;

import Decorator.BasicTicket;
import Decorator.Drink;
import Decorator.Popcorn;
import Decorator.TicketAdd;
import FactoryMethod.Ticket;
import FactoryMethod.TicketFactory;
import MVC.model.BookingService;
import MVC.model.Movie;
import MVC.model.Seat;
import MVC.model.Show;
import MVC.view.BookingView;



// Для бронирования мест в кинотеатре

public class BookingController {
    private BookingView view;
    private BookingService bookingService;

    public BookingController(BookingView view, BookingService bookingService) {
        this.view = view;
        this.bookingService = bookingService;
    }

    public void showMovies() {
        view.displayMovies(bookingService.getAvailableMovies());
    }

    public void displaySeatsForShow(Show show) {
        view.displaySeatAvailability(show.getSeats());
    }

    public void bookSeat(Show show, Seat seat, String ticketType, boolean withPopcorn, boolean withDrink) {
        boolean isBooked = bookingService.bookSeat(show, seat);
        if (isBooked) {
            Ticket ticket = TicketFactory.createTicket(ticketType);
            TicketAdd ticketAddon = new BasicTicket(ticket);

            if (withPopcorn) {
                ticketAddon = new Popcorn(ticketAddon);
            }
            if (withDrink) {
                ticketAddon = new Drink(ticketAddon);
            }

            view.displayTicketInfo(ticketAddon);
            view.displayBookingResult(true);
        } else {
            view.displayBookingResult(false);
        }
    }
}




