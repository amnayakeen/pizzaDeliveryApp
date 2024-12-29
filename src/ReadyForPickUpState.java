public class ReadyForPickUpState implements OrderState{
    @Override
    public void ordered(Order order) {
        System.out.println("Your order has already been placed");
        order.setState(new OrderedState());
    }

    @Override
    public void preparing(Order order) {
        System.out.println("Your order is already prepared");
        order.setState(new PreparedState());
    }

    @Override
    public void outForDelivery(Order order) {
        System.out.println("Your order is already sent out for delivery");
    }

    public void readyForPickup(Order order){
        System.out.println("Your order is waiting to be picked up");
    }

    @Override
    public void delivered(Order order) {
        Main.waitFor(10000);
        System.out.println("Your order is delivered");
        order.setState(new DeliveredState());
    }

    public void pickedUp(Order order){
        Main.waitFor(10000);
        System.out.println("You have picked up your order");
        order.setState(new PickedUpState());
    }
}
