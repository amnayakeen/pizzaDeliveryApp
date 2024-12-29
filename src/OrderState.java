public interface OrderState {

    void ordered(Order order);
    void preparing(Order order);
    void outForDelivery(Order order);
    void readyForPickup(Order order);
    void delivered(Order order);
    void pickedUp(Order order);
}


