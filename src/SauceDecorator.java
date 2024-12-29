public class SauceDecorator extends PizzaDecorator{

    public SauceDecorator(PizzaComponent decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getPizzaName() {
        return super.getPizzaName();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with EXTRA sauce";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + getExtraPrice();
    }

    public double getExtraPrice() {
        return 300.0;
    }
}
