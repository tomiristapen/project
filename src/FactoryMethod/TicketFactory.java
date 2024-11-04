package FactoryMethod;

import Decorator.BasicTicket;
import Decorator.TicketAdd;

public class TicketFactory {
    public static Ticket createTicket(String type) {

        switch (type) {
            case "VIP":
                return new VipTicket();

            case "Взрослый":
                return new StandardTicket();
            case "Студенческий":
                return new StudentTicket();
            case "Детский":
                return new ChildTicket();
            default:
                throw new IllegalArgumentException("Нету такого вида билета: " + type);
        }
    }

}
