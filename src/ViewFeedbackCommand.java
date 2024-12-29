class ViewFeedbackCommand implements Command {
    private final FeedbackReceiver feedbackReceiver;
    private final int orderId;

    public ViewFeedbackCommand(FeedbackReceiver feedbackReceiver, int orderId) {
        this.feedbackReceiver = feedbackReceiver;
        this.orderId = orderId;
    }

    @Override
    public void execute() {
        feedbackReceiver.viewFeedback(orderId);
    }

    @Override
    public void undo() {
        System.out.println("Viewing feedback cannot be undone.");
    }
}