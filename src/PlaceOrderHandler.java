public abstract class PlaceOrderHandler {
    protected PlaceOrderHandler nextHandler;
    private double totalPrice;


    public void setNextHandler(PlaceOrderHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(Pizza pizza, double totalPrice);


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
