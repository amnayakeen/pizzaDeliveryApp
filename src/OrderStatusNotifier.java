import java.util.*;

class OrderStatusNotifier implements Subject {
    private List<Observer> orderObservers = new ArrayList<>();
    private String status;

    @Override
    public void addObserver(Observer observer) {
        orderObservers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        orderObservers.remove(observer);
    }

    @Override
    public void notifyObservers(String status) {
        for (Observer observer : orderObservers) {
            observer.update(status);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers(status);
    }
}