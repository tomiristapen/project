package Strategy;

public class StudentDiscountStrategy implements PricingStrategy {
    private static final double STUDENT_DISCOUNT = 0.2; // 20% скидка

    @Override
    public double applyDiscount(double price) {
        return price * (1 - STUDENT_DISCOUNT);
    }
}