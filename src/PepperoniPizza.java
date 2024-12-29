public class PepperoniPizza implements PizzaComponent{

    public String getPizzaName(){
        return "Pepperoni Pizza";
    }

    public String getDescription(){
        return "Large, Thin crust, Tomato Sauce, Single Cheese, Onions, Pepperoni";
    }

    public double getPrice(){
        return 4200.0;
    }

    @Override
    public double getExtraPrice() {
        return 0;
    }
}
