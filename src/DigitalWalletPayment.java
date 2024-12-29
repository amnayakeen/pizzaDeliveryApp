import java.util.Scanner;

public class DigitalWalletPayment implements PaymentStrategy{

    public static Scanner scanner = new Scanner(System.in);

    public void makePayment( double totalAmount){
        System.out.println("Please hold your phone close to the device.");

        System.out.println("You have successfully made a payment of Rs." + totalAmount);
    }
}
