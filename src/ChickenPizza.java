public class ChickenPizza implements PizzaComponent{

    public String getPizzaName(){
        return "Chicken Pizza";
    }

    public String getDescription(){
        return "Large, Thin crust, Tomato Sauce, Single Cheese, Onions, Chicken";
    }

    public double getPrice(){
        return 4000.0;
    }

    @Override
    public double getExtraPrice() {
        return 0;
    }
}
