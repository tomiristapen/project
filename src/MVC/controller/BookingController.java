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

import java.util.List;

// Для бронирования мест в кинотеатре

public class BookingController {
    private BookingView view;
    private BookingService service;

    public BookingController(BookingView view, BookingService service) {
        this.view = view;
        this.service = service;
    }

    public void showMovies() {
        view.displayMovies(service.getAvailableMovies());
    }

    public void showSeatAvailability(Show show) {
        view.displaySeatAvailability(show.getSeats());
    }

    public void bookSeat(Show show, Seat seat) {
        boolean isBooked = service.bookSeat(show, seat);
        view.displayBookingResult(isBooked);
    }

    public void createAndBookTicket(String ticketType, Show show, List<String> addons) {

        Ticket ticket = TicketFactory.createTicket(ticketType);

        TicketAdd ticketAddon = new BasicTicket(ticket);


        for (String addon : addons) {
            switch (addon) {
                case "Попкорн":
                    ticketAddon = new Popcorn(ticketAddon);
                    break;
                case "Напиток":
                    ticketAddon = new Drink(ticketAddon);
                    break;
                default:
                    System.out.println("Услуга " + addon + " не найдена.");
                    break;
            }
        }

        view.displayTicketInfo(ticketAddon);

        Seat seat = findAvailableSeat(show);
        if (seat != null) {
            bookSeat(show, seat);
        } else {
            System.out.println("Нет доступных мест для данного показа.");
        }
    }

    private Seat findAvailableSeat(Show show) {
        return show.getSeats().stream()
                .filter(Seat::isAvailable)
                .findFirst()
                .orElse(null);
    }
}
