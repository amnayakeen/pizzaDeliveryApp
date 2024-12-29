import java.util.*;

public class CheeseHandler extends PlaceOrderHandler {

    @Override
    public void handleRequest(Pizza pizza, double totalPrice) {
        Map<String, Double> cheeseOptions = new HashMap<>();
        cheeseOptions.put("Single Cheese", 300.00);
        cheeseOptions.put("Double Cheese", 600.00);
        cheeseOptions.put("Triple Cheese", 900.00);

        System.out.println("\nChoose cheese");
        String selectedCheese = Main.getOption(cheeseOptions, Main.scanner);
        Double selectedCheesePrice = cheeseOptions.get(selectedCheese);
        pizza.setCheese(selectedCheese, selectedCheesePrice);

        totalPrice += selectedCheesePrice;
        pizza.setTotalPrice(totalPrice);

        if(nextHandler != null){
            nextHandler.handleRequest(pizza, totalPrice);
        }
    }
}
