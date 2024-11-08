package MVC.model;

import Observer.SeatObserver;

import java.util.ArrayList;
import java.util.List;

public class Seat {
    private int row;
    private int number;
    private boolean isAvailable;
    private List<SeatObserver> observers = new ArrayList<>(); // Список наблюдателей

    public Seat(int row, int number, boolean isAvailable) {
        this.row = row;
        this.number = number;
        this.isAvailable = isAvailable;
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
        this.isAvailable = available;
        notifyObservers(); // Уведомляем наблюдателей о изменении
    }

    // Метод для добавления наблюдателя
    public void addObserver(SeatObserver observer) {
        observers.add(observer);
    }

    // Метод для уведомления всех наблюдателей
    private void notifyObservers() {
        for (SeatObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", number=" + number +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
