package Decorator;

import FactoryMethod.StandardTicket;
import FactoryMethod.StudentTicket;
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

    @Override
    public String getDescription() {
        return "Билет: " + (ticket instanceof VipTicket ? "VIP" :
                ticket instanceof StandardTicket ? "Взрослый" :
                        ticket instanceof StudentTicket ? "Студенческий" :
                                "Детский");
    }

}
