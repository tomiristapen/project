// Updated BookingController.java
package MVC.controller;

import Decorator.BasicTicket;
import Decorator.Drink;
import Decorator.Popcorn;
import Decorator.TicketAdd;
import FactoryMethod.Ticket;
import FactoryMethod.TicketFactory;
import MVC.model.BookingService;
import MVC.model.Seat;
import MVC.model.Show;
import MVC.view.BookingView;
import Observer.SeatObserver;
import Strategy.PricingStrategy;

public class BookingController {
    private BookingView view;
    private BookingService bookingService;
    private PricingStrategy pricingStrategy;

    public BookingController(BookingView view, BookingService bookingService, PricingStrategy pricingStrategy) {
        this.view = view;
        this.bookingService = bookingService;
        this.pricingStrategy = pricingStrategy;
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

            double basePrice = 10.0;
            double discountedPrice = pricingStrategy.applyDiscount(basePrice);

            view.displayTicketInfo(ticketAddon);
            view.displayBookingResult(true, discountedPrice);
        } else {
            view.displayBookingResult(false, 0.0);
        }
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public void addSeatObserver(Seat seat, SeatObserver observer) {
        seat.addObserver(observer);
    }
}
