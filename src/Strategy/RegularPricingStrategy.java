package Strategy;

public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public double applyDiscount(double price) {
        return price;
    }
}
