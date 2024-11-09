package MVC.controller;

import Decorator.*;
import Facade.PaymentFacade;
import FactoryMethod.Ticket;
import FactoryMethod.TicketFactory;
import MVC.model.BookingService;
import MVC.model.Seat;
import MVC.model.Show;
import MVC.view.BookingView;
import Observer.SeatObserver;
import Strategy.MorningShowDiscountStrategy;
import Strategy.PricingStrategy;
import Strategy.RegularPricingStrategy;

import java.time.LocalTime;

public class BookingController {
    private BookingView view;
    private BookingService bookingService;
    private PricingStrategy pricingStrategy;
    private PaymentFacade paymentFacade;

    public BookingController(BookingView view, BookingService bookingService, PricingStrategy pricingStrategy, PaymentFacade paymentFacade) {
        this.view = view;
        this.bookingService = bookingService;
        this.pricingStrategy = pricingStrategy;
        this.paymentFacade = paymentFacade;
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

            double basePrice = ticket.getPrice();
            double totalPrice = basePrice + ticketAddon.getCost();

            if (show.getShowTime().toLocalTime().isBefore(LocalTime.NOON)) {
                pricingStrategy = new MorningShowDiscountStrategy(); // Apply morning show discount
            } else {
                pricingStrategy = new RegularPricingStrategy(); // Regular pricing if not morning
            }

            double discountedPrice = pricingStrategy.applyDiscount(totalPrice);

            view.displayTicketInfo(ticketAddon);
            view.displayBookingResult(true, discountedPrice);
        } else {
            view.displayBookingResult(false, 0.0);
        }
    }

    private void processPayment(String paymentType, double amount) {
        boolean isPaymentSuccessful = paymentFacade.processPayment(paymentType, amount);
        if (isPaymentSuccessful) {
            view.displayPaymentResult("Оплата успешна. Тип: " + paymentType + ", сумма: " + amount + " тенге");
        } else {
            view.displayPaymentResult("Оплата не выполнена. Пожалуйста, попробуйте снова.");
        }
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public void addSeatObserver(Seat seat, SeatObserver observer) {
        seat.addObserver(observer);
    }
}
