import java.util.Scanner;

public class DebitCardPayment implements PaymentStrategy{

    public static Scanner scanner = new Scanner(System.in);

    public void makePayment(double totalAmount){
        System.out.print("\nEnter your debit card number: ");
        int cardNumber = scanner.nextInt();

        System.out.print("\nEnter OTP: ");
        int otp = scanner.nextInt();

        System.out.println("You have successfully made a payment of Rs." + totalAmount);
    }
}
