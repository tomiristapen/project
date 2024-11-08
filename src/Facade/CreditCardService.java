package Facade;

class CreditCardService implements PaymentService {
    @Override
    public boolean processPayment(double amount) {

        System.out.println("Оплата картой: " + amount);
        return true;
    }


    @Override
    public String checkStatus(String transactionId) {
        return "Completed";
    }
}
