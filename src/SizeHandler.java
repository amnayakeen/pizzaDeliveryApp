import java.util.*;

public class SizeHandler extends PlaceOrderHandler {

    @Override
    public void handleRequest(Pizza pizza, double totalPrice) {
        Map<String, Double> sizeOptions = new HashMap<>();
        sizeOptions.put("Small", 1000.00);
        sizeOptions.put("Medium", 2000.00);
        sizeOptions.put("Large", 3000.00);

        System.out.println("\nChoose a size");
        String selectedSize = Main.getOption(sizeOptions, Main.scanner);
        Double selectedSizePrice = sizeOptions.get(selectedSize);
        pizza.setSize(selectedSize, selectedSizePrice);

        totalPrice += selectedSizePrice;

        if(nextHandler != null){
            nextHandler.handleRequest(pizza, totalPrice);
        }
    }
}
