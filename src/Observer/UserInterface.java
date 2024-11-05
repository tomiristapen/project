package Observer;

import MVC.model.Seat;

public class UserInterface implements SeatObserver {

    @Override
    public void update(Seat seat) {
        System.out.println("Seat at Row " + seat.getRow() + ", Number " + seat.getNumber() + " is now " +
                (seat.isAvailable() ? "available" : "booked"));
    }
}
