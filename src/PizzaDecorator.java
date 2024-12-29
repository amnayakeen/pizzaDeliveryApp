public class PizzaDecorator implements PizzaComponent{

    protected PizzaComponent decoratedPizza;

    public PizzaDecorator(PizzaComponent decoratedPizza) {
        this.decoratedPizza = decoratedPizza;
    }

    public PizzaDecorator() {

    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription();
    }

    @Override
    public double getPrice() {
        return decoratedPizza.getPrice();
    }

    @Override
    public String getPizzaName() {
        return decoratedPizza.getPizzaName();
    }

    public double getExtraPrice() {
        return decoratedPizza.getPrice();
    }
}
