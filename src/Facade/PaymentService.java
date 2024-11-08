package Facade;

public interface PaymentService {
    boolean processPayment(double amount);
    String checkStatus(String transactionId);
}
