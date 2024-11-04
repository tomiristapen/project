package Decorator;

import FactoryMethod.Ticket;
import FactoryMethod.VipTicket;

public class BasicTicket implements TicketAdd{
    private Ticket ticket;

    public BasicTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public double getCost() {
        ticket.calculatePrice();
        return ticket.getPrice();
    }

}
