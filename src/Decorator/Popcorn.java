package Decorator;

public class Popcorn implements TicketAdd {

    private TicketAdd ticketAdd;

    public Popcorn(TicketAdd ticketAddon) {
        this.ticketAdd  = ticketAddon;
    }

    public double getCost() {
        return ticketAdd.getCost() + 3500.0;
    }

    public String getDescription() {
        return ticketAdd.getDescription() + ", с попкорном";
    }

}
