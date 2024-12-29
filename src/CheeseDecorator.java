public class CheeseDecorator extends PizzaDecorator{

    public CheeseDecorator(PizzaComponent decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getPizzaName() {
        return super.getPizzaName();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with EXTRA cheese";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + getExtraPrice();
    }

    public double getExtraPrice() {
        return 200.0;
    }
}
