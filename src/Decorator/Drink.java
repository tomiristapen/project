package Decorator;

public class Drink implements TicketAdd{

    private TicketAdd ticketAdd;

    public Drink(TicketAdd ticketAddon) {
        this.ticketAdd  = ticketAddon;
    }

    public double getCost() {
        return ticketAdd.getCost() + 500.0;
    }

    public String getDescription() {
        return ticketAdd.getDescription() + ", с напитком";
    }
}
