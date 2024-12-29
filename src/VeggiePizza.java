public class VeggiePizza implements PizzaComponent{

    public String getPizzaName(){
        return "Veggie Pizza";
    }

    public String getDescription(){
        return "Large, Thin crust, Tomato Sauce, Single Cheese, Onions, Mushroom";
    }

    public double getPrice(){
        return 3900.0;
    }

    @Override
    public double getExtraPrice() {
        return 0;
    }
}
