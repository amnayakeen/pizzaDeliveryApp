public class PreparedState implements OrderState{
    @Override
    public void ordered(Order order) {
        System.out.println("Your order has already been placed");
        order.setState(new OrderedState());
    }

    @Override
    public void preparing(Order order) {
        System.out.println("Your order is already prepared");
    }

    @Override
    public void outForDelivery(Order order) {
        Main.waitFor(2000);
        System.out.println("Your order will be delivered in 10 minutes");
        order.setState(new OutForDeliveryState());
        Main.waitFor(8000);
    }

    public void readyForPickup(Order order){
        Main.waitFor(1000);
        System.out.println("Your order is ready to be picked up");
        order.setState(new ReadyForPickUpState());
        Main.waitFor(8000);
    }

    @Override
    public void delivered(Order order) {
        System.out.println("Your order will be delivered");
        order.setState(new DeliveredState());
    }

    public void pickedUp(Order order){
        System.out.println("You have to pick up your order");
        order.setState(new PickedUpState());
    }
}
