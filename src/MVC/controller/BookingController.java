package MVC.controller;

import MVC.model.*;
import MVC.view.BookingView;
import Facade.PaymentFacade;
import Decorator.*;
import FactoryMethod.Ticket;
import FactoryMethod.TicketFactory;
import Singleton.NotificationManager;
import Observer.SeatObserver;
import Strategy.PricingStrategy;

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

            double basePrice = 10.0;
            double discountedPrice = pricingStrategy.applyDiscount(basePrice);

            view.displayTicketInfo(ticketAddon);
            view.displayBookingResult(true, discountedPrice);

            NotificationManager.getInstance().sendNotification(
                    "Место успешно забронировано: Ряд " + seat.getRow() + ", Номер " + seat.getNumber()
            );
        } else {
            view.displayBookingResult(false, 0.0);
        }
    }

    public void processPayment(String paymentType, double amount) {
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
