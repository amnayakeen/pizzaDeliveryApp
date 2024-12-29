import java.util.*;

public class Pizza {
    static Map<String, Double> pizzaMenu = new HashMap<>();

    private String pizzaName;
    private String size;
    private Double sizePrice;
    private String crust;
    private Double crustPrice;
    private String sauce;
    private Double saucePrice;
    private String topping;
    private Double toppingPrice;
    private String cheese;
    private Double cheesePrice;
    private Double totalPrice;

    public Pizza(){}

    public Pizza(PizzaBuilder builder) {
        this.pizzaName = builder.pizzaName;
        this.size = builder.size;
        this.sizePrice = builder.sizePrice;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.topping = builder.topping;
        this.cheese = builder.cheese;
        this.totalPrice = builder.totalPrice;
    }

    public static double getPizzaMenu(){
        PizzaComponent chickenPizza = new ChickenPizza();
        PizzaComponent pepperoniPizza = new PepperoniPizza();
        PizzaComponent veggiePizza = new VeggiePizza();

        pizzaMenu.put(chickenPizza.getPizzaName() + ": " + chickenPizza.getDescription(), chickenPizza.getPrice());
        pizzaMenu.put(pepperoniPizza.getPizzaName() + ": " + pepperoniPizza.getDescription(), pepperoniPizza.getPrice());
        pizzaMenu.put(veggiePizza.getPizzaName() + ": " + veggiePizza.getDescription(), veggiePizza.getPrice());

        System.out.println("\nPizza Menu:");
        String selectedPizza = Main.getOption(pizzaMenu, Main.scanner);
        return pizzaMenu.get(selectedPizza);
    }

    @Override
    public String toString() {
        return "\nSize: " + size + "\t\t\t\t\t\t\t\t\t\t\t\t\t" + sizePrice + "\nCrust: " + crust + "\t\t\t\t\t\t\t\t\t\t" + crustPrice +
                "\nSauce: " + sauce + "\t\t\t\t\t\t\t\t\t\t" + saucePrice + "\nTopping: " + topping + "\t" + toppingPrice +
                "\nCheese: " + cheese + "\t\t\t\t\t\t\t\t\t\t" + cheesePrice + "\nYour total: \t\t\t\t\t\t\t\t\t\t\t\t" + totalPrice + "\n";
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size, Double sizePrice) {
        this.size = size;
        this.sizePrice = sizePrice;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust, Double crustPrice) {
        this.crust = crust;
        this.crustPrice = crustPrice;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce, Double saucePrice) {
        this.sauce = sauce;
        this.saucePrice = saucePrice;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping, Double toppingPrice) {
        this.topping = topping;
        this.toppingPrice = toppingPrice;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese, Double cheesePrice) {
        this.cheese = cheese;
        this.cheesePrice = cheesePrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getSizePrice() {
        return sizePrice;
    }

    public Double getCrustPrice() {
        return crustPrice;
    }

    public Double getSaucePrice() {
        return saucePrice;
    }

    public Double getToppingPrice() {
        return toppingPrice;
    }

    public Double getCheesePrice() {
        return cheesePrice;
    }

}
