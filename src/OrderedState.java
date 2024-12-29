public class OrderedState implements OrderState{
    @Override
    public void ordered(Order order) {
        System.out.println("Your order has been placed");
    }

    @Override
    public void preparing(Order order) {
        Main.waitFor(1000);
        System.out.println("Your order will be prepared in 20 minutes");
        Main.waitFor(5000);
        order.setState(new PreparedState());
    }

    @Override
    public void outForDelivery(Order order) {
        System.out.println("Your order is being sent out for delivery");
        order.setState(new OutForDeliveryState());
    }

    public void readyForPickup(Order order){
        System.out.println("Your order is getting ready to be picked up");
        order.setState(new ReadyForPickUpState());
    }

    @Override
    public void delivered(Order order) {
        System.out.println("Your order is being delivered");
        order.setState(new DeliveredState());
    }

    public void pickedUp(Order order){
        System.out.println("You have to pick up your order");
        order.setState(new PickedUpState());
    }
}
