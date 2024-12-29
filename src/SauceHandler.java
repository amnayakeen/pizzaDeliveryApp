import java.util.*;

public class SauceHandler extends PlaceOrderHandler {

    @Override
    public void handleRequest(Pizza pizza, double totalPrice) {
        Map<String, Double> sauceOptions = new HashMap<>();
        sauceOptions.put("Tomato Sauce", 300.50);
        sauceOptions.put("BBQ Sauce", 500.00);
        sauceOptions.put("Alfredo Sauce", 600.00);

        System.out.println("\nChoose sauce");
        String selectedSauce = Main.getOption(sauceOptions, Main.scanner);
        double selectedSaucePrice = sauceOptions.get(selectedSauce);
        pizza.setSauce(selectedSauce, selectedSaucePrice);

        totalPrice += selectedSaucePrice;

        if(nextHandler != null){
            nextHandler.handleRequest(pizza, totalPrice);
        }
    }
}
