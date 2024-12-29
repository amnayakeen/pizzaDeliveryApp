import java.util.HashMap;
import java.util.Map;

public class CrustHandler extends PlaceOrderHandler {

    @Override
    public void handleRequest(Pizza pizza, double totalPrice) {
        Map<String, Double> crustOptions = new HashMap<>();
        crustOptions.put("Thin Crust", 900.00);
        crustOptions.put("Thick Crust", 700.50);
        crustOptions.put("Stuffed Crust", 1100.50);

        System.out.println("\nChoose topping");
        String selectedCrust = Main.getOption(crustOptions, Main.scanner);
        double selectedCrustPrice = crustOptions.get(selectedCrust);
        pizza.setCrust(selectedCrust, selectedCrustPrice);

        totalPrice += selectedCrustPrice;


        if(nextHandler != null){
            nextHandler.handleRequest(pizza, totalPrice);
        }
    }
}
