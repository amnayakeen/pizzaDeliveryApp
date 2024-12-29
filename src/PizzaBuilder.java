import java.util.List;

public class PizzaBuilder {
    String pizzaName;
    String size;
    Double sizePrice;
    String crust;
    String sauce;
    String topping;
    String cheese;
    Double totalPrice;

    public PizzaBuilder() {}


    public Pizza build(){
        Pizza pizza = new Pizza();
        return new Pizza(this);
   }
}
