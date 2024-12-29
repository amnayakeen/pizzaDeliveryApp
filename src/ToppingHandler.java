import java.util.*;

public class ToppingHandler extends PlaceOrderHandler {

    @Override
    public void handleRequest(Pizza pizza, double totalPrice) {
        Map<String, Double> toppingOptions = new HashMap<>();
        toppingOptions.put("Pepperoni", 500.00);
        toppingOptions.put("Mushrooms", 500.75);
        toppingOptions.put("Onions", 200.50);
        toppingOptions.put("Sausage", 300.25);
        toppingOptions.put("Pineapple", 300.50);

        List<String> selectedTopping = new ArrayList<>();
        double selectedToppingPrice = 0.0;
        String toStringSelectedToppings = "";

        System.out.println("\nChoose 1 or more toppings (type '0' to finish):");
        selectedTopping = Main.getMultipleOptions(toppingOptions, Main.scanner);
        for (String topping : selectedTopping) {
            selectedToppingPrice += toppingOptions.get(topping);
        }
        toStringSelectedToppings = toStringSelectedToppings + selectedTopping;

        pizza.setTopping(toStringSelectedToppings, selectedToppingPrice);

        totalPrice += selectedToppingPrice;

        if(nextHandler != null){
            nextHandler.handleRequest(pizza, totalPrice);
        }
    }
}
