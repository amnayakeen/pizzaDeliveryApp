public class DeliveredState implements OrderState{
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
        System.out.println("Your order has already been sent out for delivery");
        order.setState(new OutForDeliveryState());
    }

    public void readyForPickup(Order order){
        System.out.println("Your order is already picked up");
        order.setState(new ReadyForPickUpState());
    }

    @Override
    public void delivered(Order order) {
        System.out.println("Your order is delivered");
    }

    public void pickedUp(Order order){
        System.out.println("You have picked up your order");
    }
}
