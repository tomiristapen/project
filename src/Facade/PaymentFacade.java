package Facade;

public class PaymentFacade {
    private final PaymentService cashService;
    private final PaymentService creditCardService;

    public PaymentFacade() {
        this.creditCardService = new CreditCardService();
        this. cashService= new CashService();
    }

    public boolean processPayment(String paymentType, double amount) {
        switch (paymentType.toLowerCase()) {
            case "Карта":
                return creditCardService.processPayment(amount);
            case "Наличные":
                return cashService.processPayment(amount);
            default:
                throw new IllegalArgumentException("Способ оплаты: " + paymentType);
        }
    }


    public String checkPaymentStatus(String paymentType, String transactionId) {
        switch (paymentType.toLowerCase()) {
            case "Карта":
                return creditCardService.checkStatus(transactionId);
            case "Наличные":
                return cashService.checkStatus(transactionId);
            default:
                throw new IllegalArgumentException("Оплата не прошла: " + paymentType);
        }
    }
}
