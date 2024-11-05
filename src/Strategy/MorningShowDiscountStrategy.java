package Strategy;

public class MorningShowDiscountStrategy implements PricingStrategy {
    private static final double MORNING_SHOW_DISCOUNT = 0.15; // 15% скидка

    @Override
    public double applyDiscount(double price) {
        return price * (1 - MORNING_SHOW_DISCOUNT);
    }
}
