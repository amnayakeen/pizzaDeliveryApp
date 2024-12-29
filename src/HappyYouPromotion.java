public class HappyYouPromotion implements SeasonalPromotionAndDiscountsStrategy{
    public Double applyDiscount(double totalAmount){
        System.out.println("Congratulations! You just got a FREE Pepperoni Pizza (Medium). \n" + "Happy You, Happy Us! Enjoy!!");
        return totalAmount;
    }
}
