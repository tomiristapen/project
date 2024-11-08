package Facade;

public class CashService implements PaymentService {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Оплата наличкой: " + amount);
        return true;
    }

    @Override
    public String checkStatus(String transactionId) {
        return "Completed";
    }
}

