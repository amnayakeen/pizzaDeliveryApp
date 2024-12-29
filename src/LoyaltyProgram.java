import java.util.*;

public class LoyaltyProgram {
    public static Scanner scanner = new Scanner(System.in);
    public static Map<Integer, Double> loyalty = new HashMap<>();

    // adds 10 loyalty points after every order
    public static void addLoyaltyAmount(int customerID, String customerName){
        loyalty.put(customerID, loyalty.getOrDefault(customerID, 0.0) + 10.0);
        System.out.println("Added 10 loyalty points for " + customerName);
        getLoyaltyAmount(customerID, customerName);
    }

    public static void getLoyaltyAmount(int customerID, String customerName){
        if (loyalty.containsKey(customerID)) {
            System.out.println("Loyalty points for " + customerName + ": " + loyalty.get(customerID));
        } else {
            System.out.println("No loyalty points found for " + customerName);
        }
    }

    public static Double useLoyaltyAmount(int customerID, double finalPrice){
        double loyaltyAmount = loyalty.getOrDefault(customerID, 0.0);
        String freeItems = "";

        if(loyaltyAmount >= 10 && loyaltyAmount < 20){
            System.out.println("You are eligible for a 5% discount");
            finalPrice *= 0.95;
        } else if(loyaltyAmount >= 20 && loyaltyAmount < 50){
            System.out.println("You are eligible for a FREE Chicken Pizza (Small)");
            freeItems = "Congratulations! You get a Free Chicken Pizza (Small)";
        } else if(loyaltyAmount >= 50){
            System.out.println("You are eligible for a 30% discount");
            finalPrice *= 0.7;
        } else {
            System.out.println("Sorry, you are not eligible for any offers at the moment");
        }

        if(loyaltyAmount >= 10){
            System.out.print("\nDo you want to claim your loyalty reward? (enter 1 if yes):");
            int yesOrNo = scanner.nextInt();

            // if loyalty offer is being used then 10 loyalty points is deducted
            if(yesOrNo == 1){
                loyalty.put(customerID, loyalty.getOrDefault(customerID, 0.0) - 10.0);
                System.out.println("Your final total is " + finalPrice);
                System.out.println(freeItems);
            }
        }

        return finalPrice;
    }
}
