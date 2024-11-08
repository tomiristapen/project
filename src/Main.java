
import Facade.PaymentFacade;
import MVC.controller.BookingController;
import MVC.model.BookingService;
import MVC.model.Movie;
import MVC.model.Seat;
import MVC.model.Show;
import MVC.view.BookingView;
import Strategy.PricingStrategy;
import Strategy.RegularPricingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creating some sample movies
        List<Show> movie1Shows = new ArrayList<>();
        Movie movie1 = new Movie("Movie 1", "Action", movie1Shows);

        List<Show> movie2Shows = new ArrayList<>();
        Movie movie2 = new Movie("Movie 2", "Drama", movie2Shows);

        // Creating shows for movies
        Show show1 = new Show(movie1, LocalDateTime.of(2024, 11, 10, 10, 0));
        Show show2 = new Show(movie2, LocalDateTime.of(2024, 11, 10, 12, 0));


        movie1.addShow(show1);
        movie2.addShow(show2);

        // Sample movies list
        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        // Create View, Service, and Controller
        BookingView view = new BookingView();
        BookingService bookingService = new BookingService(movies);
        PricingStrategy pricingStrategy = new RegularPricingStrategy(); // Default strategy
        PaymentFacade paymentFacade = new PaymentFacade();
        BookingController controller = new BookingController(view, bookingService, pricingStrategy, paymentFacade);

        // Main interaction loop
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Cinema Booking System!");
            System.out.println("1. Show available movies");
            System.out.println("2. Select a movie and view seats");
            System.out.println("3. Book a seat");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    controller.showMovies();
                    break;
                case 2:
                    System.out.println("Enter movie number to select (1 or 2):");
                    int movieChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Movie selectedMovie = movies.get(movieChoice - 1);
                    System.out.println("Available shows for " + selectedMovie.getTitle() + ":");
                    for (int i = 0; i < selectedMovie.getShows().size(); i++) {
                        Show show = selectedMovie.getShows().get(i);
                        System.out.println((i + 1) + ". Show time: " + show.getShowTime());
                    }
                    System.out.println("Enter show number to view seats:");
                    int showChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Show selectedShow = selectedMovie.getShows().get(showChoice - 1);
                    controller.displaySeatsForShow(selectedShow);
                    break;
                case 3:
                    System.out.println("Enter movie number to book (1 or 2):");
                    int bookMovieChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Movie bookMovie = movies.get(bookMovieChoice - 1);
                    System.out.println("Enter show number to book for movie " + bookMovie.getTitle() + ":");
                    for (int i = 0; i < bookMovie.getShows().size(); i++) {
                        Show show = bookMovie.getShows().get(i);
                        System.out.println((i + 1) + ". Show time: " + show.getShowTime());
                    }
                    int bookShowChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Show bookShow = bookMovie.getShows().get(bookShowChoice - 1);

                    System.out.println("Enter seat row (1-5):");
                    int row = scanner.nextInt();
                    System.out.println("Enter seat number (1-10):");
                    int seatNumber = scanner.nextInt();
                    Seat selectedSeat = bookShow.getSeats().stream()
                            .filter(seat -> seat.getRow() == row && seat.getNumber() == seatNumber)
                            .findFirst()
                            .orElse(null);

                    if (selectedSeat != null && selectedSeat.isAvailable()) {
                        System.out.println("Would you like to add popcorn (yes/no)?");
                        boolean withPopcorn = scanner.nextLine().equalsIgnoreCase("yes");

                        System.out.println("Would you like to add a drink (yes/no)?");
                        boolean withDrink = scanner.nextLine().equalsIgnoreCase("yes");

                        // Book the seat and apply pricing strategy
                        controller.bookSeat(bookShow, selectedSeat, "Basic", withPopcorn, withDrink);
                    } else {
                        System.out.println("This seat is not available.");
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the Cinema Booking System!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }
}
