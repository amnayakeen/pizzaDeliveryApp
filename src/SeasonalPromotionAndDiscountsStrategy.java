import java.util.Scanner;

public interface SeasonalPromotionAndDiscountsStrategy {

    public static Scanner scanner = new Scanner(System.in);

    Double applyDiscount(double totalAmount);

    public static Double choosePromotionOrDiscount(double totalAmount){
        SeasonalPromotionAndDiscountsStrategy happyYouPromotion = new HappyYouPromotion();
        SeasonalPromotionAndDiscountsStrategy halfPriceDiscount = new HalfPriceDiscount();
        SeasonalPromotionAndDiscountsStrategy newYearPromotion = new NewYearPromotion();

        System.out.println("\nOur ongoing promotions. Don't miss out!");
        System.out.println("1. Happy you, Happy us. FREE Pepperoni Pizza (Medium)");
        System.out.println("2. Half the price, double the delight. 50% OFF ");
        System.out.println("3. Enjoy New Year's Eve with Extravagance, 50% OFF along with FREE Chicken Pizza (Medium)");
        System.out.print("\nChoose your promotion (1, 2 or 3): ");
        int chosenPromotionOrDiscount = scanner.nextInt();

        if(chosenPromotionOrDiscount == 1){
            totalAmount = happyYouPromotion.applyDiscount(totalAmount);
        } else if(chosenPromotionOrDiscount == 2){
            totalAmount = halfPriceDiscount.applyDiscount(totalAmount);
        } else if(chosenPromotionOrDiscount == 3){
            totalAmount = newYearPromotion.applyDiscount(totalAmount);
        } else{
            System.out.println("Invalid Option");
            choosePromotionOrDiscount(totalAmount);
        }

        return totalAmount;
    }
}
