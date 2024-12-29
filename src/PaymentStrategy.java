import java.util.Scanner;

public interface PaymentStrategy {

    static Scanner scanner = new Scanner(System.in);
    
    void makePayment(double totalAmount);
    
    public static void choosePaymentMethod(int pickupOrDelivery, double totalAmount){
        int paymentMethod = 0;
        PaymentStrategy creditCardMethod = new CreditCardPayment();
        PaymentStrategy debitCardMethod = new DebitCardPayment();
        PaymentStrategy digitalWalletMethod = new DigitalWalletPayment();

        if(pickupOrDelivery == 1){
            System.out.println("\n1. Credit Card Payment");
            System.out.println("2. Debit Card Payment");
            System.out.println("3. Digital Wallet Payment");
            System.out.print("\nChoose your payment method (1, 2 or 3): ");
            paymentMethod = scanner.nextInt();

            if (paymentMethod == 1){
                creditCardMethod.makePayment(totalAmount);
            } else if(paymentMethod == 2){
                debitCardMethod.makePayment(totalAmount);
            } else if(paymentMethod == 3){
                digitalWalletMethod.makePayment(totalAmount);
            }else{
                System.out.println("Invalid option");
                choosePaymentMethod(pickupOrDelivery, totalAmount);
            }
        } else if (pickupOrDelivery == 2){
            System.out.println("\n1. Credit Card Payment");
            System.out.println("2. Debit Card Payment");
            System.out.print("\nChoose your payment method (1 or 2): ");
            paymentMethod = scanner.nextInt();

            if (paymentMethod == 1){
                creditCardMethod.makePayment(totalAmount);
            } else if(paymentMethod == 2){
                debitCardMethod.makePayment(totalAmount);
            } else{
                System.out.println("Invalid option");
                choosePaymentMethod(pickupOrDelivery, totalAmount);
            }
        }

    }
}
