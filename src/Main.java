import Singleton.NotificationManager;
import MVC.controller.BookingController;
import MVC.model.BookingService;
import MVC.model.Movie;
import MVC.model.Seat;
import MVC.model.Show;
import MVC.view.BookingView;
import Strategy.PricingStrategy;
import Strategy.RegularPricingStrategy;
import Facade.PaymentFacade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Show> movie1Shows = new ArrayList<>();
        Movie movie1 = new Movie("Шрек", "Комедия", movie1Shows);

        List<Show> movie2Shows = new ArrayList<>();
        Movie movie2 = new Movie("Гарри Поттер", "Фэнтези", movie2Shows);

        Show show1 = new Show(movie1, LocalDateTime.of(2024, 11, 10, 10, 0));
        Show show1Evening = new Show(movie1, LocalDateTime.of(2024, 11, 10, 18, 0));
        Show show2 = new Show(movie2, LocalDateTime.of(2024, 11, 10, 12, 0));
        Show show2Evening = new Show(movie2, LocalDateTime.of(2024, 11, 10, 20, 0));

        movie1.addShow(show1);
        movie1.addShow(show1Evening);
        movie2.addShow(show2);
        movie2.addShow(show2Evening);

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        BookingView view = new BookingView();
        BookingService bookingService = new BookingService(movies);
        PricingStrategy pricingStrategy = new RegularPricingStrategy();
        PaymentFacade paymentFacade = new PaymentFacade();
        BookingController controller = new BookingController(view, bookingService, pricingStrategy, paymentFacade);

        NotificationManager notificationManager = NotificationManager.getInstance();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nДобро пожаловать в систему бронирования билетов в кино!");
            System.out.println("1. Показать доступные фильмы");
            System.out.println("2. Выбрать фильм и просмотреть места");
            System.out.println("3. Забронировать место");
            System.out.println("4. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    controller.showMovies();
                    break;
                case 2:
                    System.out.println("Введите номер фильма для выбора (1 или 2):");
                    int movieChoice = scanner.nextInt();
                    scanner.nextLine();
                    Movie selectedMovie = movies.get(movieChoice - 1);
                    System.out.println("Доступные показы для " + selectedMovie.getTitle() + ":");

                    for (int i = 0; i < selectedMovie.getShows().size(); i++) {
                        Show show = selectedMovie.getShows().get(i);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        System.out.println((i + 1) + ". Время сеанса: " + show.getShowTime().format(formatter));

                        if (show.getShowTime().getHour() < 12) {
                            System.out.println("Скидка на утренний сеанс!");
                            notificationManager.sendNotification("Скидка на утренний сеанс доступна!");
                        }
                    }

                    System.out.println("Введите номер показа для просмотра мест:");
                    int showChoice = scanner.nextInt();
                    scanner.nextLine();
                    Show selectedShow = selectedMovie.getShows().get(showChoice - 1);
                    controller.displaySeatsForShow(selectedShow);
                    break;
                case 3:
                    System.out.println("Введите номер фильма для бронирования (1 или 2):");
                    int bookMovieChoice = scanner.nextInt();
                    scanner.nextLine();
                    Movie bookMovie = movies.get(bookMovieChoice - 1);
                    System.out.println("Введите номер показа для бронирования фильма " + bookMovie.getTitle() + ":");
                    for (int i = 0; i < bookMovie.getShows().size(); i++) {
                        Show show = bookMovie.getShows().get(i);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        System.out.println((i + 1) + ". Время сеанса: " + show.getShowTime().format(formatter));

                        if (show.getShowTime().getHour() < 12) {
                            System.out.println("Скидка на утренний сеанс!");
                        }
                    }
                    int bookShowChoice = scanner.nextInt();
                    scanner.nextLine();
                    Show bookShow = bookMovie.getShows().get(bookShowChoice - 1);

                    System.out.println("Введите ряд места (1-5):");
                    int row = scanner.nextInt();
                    System.out.println("Введите номер места (1-10):");
                    int seatNumber = scanner.nextInt();
                    Seat selectedSeat = bookShow.getSeats().stream()
                            .filter(seat -> seat.getRow() == row && seat.getNumber() == seatNumber)
                            .findFirst()
                            .orElse(null);

                    if (selectedSeat != null && selectedSeat.isAvailable()) {
                        System.out.println("Хотите дополнительные опции?");
                        System.out.println("1. Попкорн(3500)");
                        System.out.println("2. Напиток(500)");
                        System.out.println("3. Попкорн и напиток");
                        System.out.println("4. Нет");
                        int addOnChoice = scanner.nextInt();
                        scanner.nextLine();

                        boolean withPopcorn = addOnChoice == 1 || addOnChoice == 3;
                        boolean withDrink = addOnChoice == 2 || addOnChoice == 3;

                        System.out.println("Введите тип билета (VIP(2500), Взрослый(1900), Студенческий(1600), Детский(1400)):");
                        String ticketType = scanner.nextLine().trim();

                        controller.bookSeat(bookShow, selectedSeat, ticketType, withPopcorn, withDrink);

                        System.out.println("Выберите тип оплаты: 1 - Карта, 2 - Наличные");
                        String paymentType = scanner.nextLine().trim();

                        System.out.println("Оплата успешна. Тип: " + paymentType + "\n");
                        notificationManager.sendNotification("Бронирование завершено успешно для фильма " + bookMovie.getTitle() + " на сеанс " + bookShow.getShowTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                    } else {
                        System.out.println("Это место недоступно.");
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println("Спасибо за использование системы бронирования билетов в кино!");
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }
}
