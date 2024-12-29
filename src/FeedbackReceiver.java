import java.util.*;

class FeedbackReceiver {
    private final Map<Integer, String> feedbackMap = new HashMap<>();

    public void getFeedback(int orderId, String feedback) {
        feedbackMap.put(orderId, feedback);
        System.out.println("Feedback collected for Order ID " + orderId + ": " + feedback);
    }

    public void viewFeedback(int orderId) {
        String feedback = feedbackMap.get(orderId);
        if (feedback != null) {
            System.out.println("Feedback for Order ID " + orderId + ": " + feedback);
        } else {
            System.out.println("No feedback found for Order ID " + orderId);
        }
    }

    public void undoFeedback(int orderId) {
        if (feedbackMap.containsKey(orderId)) {
            feedbackMap.remove(orderId);
            System.out.println("Feedback for Order ID " + orderId + " has been removed.");
        } else {
            System.out.println("No feedback to undo for Order ID " + orderId);
        }
    }
}
