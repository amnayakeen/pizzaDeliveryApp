public class PackageDecorator extends PizzaDecorator{

    public PackageDecorator(PizzaComponent decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getPizzaName() {
        return super.getPizzaName();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Special Packaging";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + getExtraPrice();
    }

    public double getExtraPrice() {
        return 350.0;
    }
}
