// Updated Seat.java
package MVC.model;

import Observer.SeatObserver;
import java.util.ArrayList;
import java.util.List;

public class Seat {
    private int row;
    private int number;
    private boolean isAvailable;
    private List<SeatObserver> observers;

    public Seat(int row, int number) {
        this.row = row;
        this.number = number;
        this.isAvailable = true;
        this.observers = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        if (this.isAvailable != available) {
            this.isAvailable = available;
            notifyObservers();
        }
    }

    public void toggleAvailability() {
        setAvailable(!this.isAvailable);
    }

    public void addObserver(SeatObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(SeatObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (SeatObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row: " + row +
                ", number: " + number +
                ", isAvailable: " + isAvailable +
                '}';
    }
}