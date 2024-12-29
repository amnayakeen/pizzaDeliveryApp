public class HalfPriceDiscount implements SeasonalPromotionAndDiscountsStrategy{
    public Double applyDiscount(double totalAmount){
        totalAmount *= 0.5;
        System.out.println("Congratulations! You just got a 50% discount." + "\nYour final total is " + totalAmount);
        return totalAmount;
    }
}
