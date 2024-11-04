package FactoryMethod;

public abstract class Ticket {
    double price;

    public double getPrice() {
        return price;
    }

    public abstract void calculatePrice();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " Цена билета: " + price;
    }
}
