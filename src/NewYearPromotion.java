public class NewYearPromotion implements SeasonalPromotionAndDiscountsStrategy{
    public Double applyDiscount(double totalAmount){
        totalAmount *= 0.5;
        System.out.println("Congratulations! You got yourself a FREE Chicken Pizza (Medium) as well as a 50% discount." +
                "\nYour final total is " + totalAmount);
        return totalAmount;
    }
}
