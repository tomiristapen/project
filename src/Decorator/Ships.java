package Decorator;

public class Ships implements TicketAdd{

    private TicketAdd ticketAdd;

    public Ships(TicketAdd ticketAddon) {
        this.ticketAdd  = ticketAddon;
    }

    public double getCost() {
        return ticketAdd.getCost() + 1000.0;
    }

    public String getDescription() {
        return ticketAdd.getDescription() + ", с чипсами";
    }
}
