class GetFeedbackCommand implements Command {
    private FeedbackReceiver feedbackReceiver;
    private int orderId;
    private String feedback;


    public GetFeedbackCommand(FeedbackReceiver feedbackReceiver, int orderId, String feedback) {
        this.feedbackReceiver = feedbackReceiver;
        this.orderId = orderId;
        this.feedback = feedback;
    }

    @Override
    public void execute() {
        feedbackReceiver.getFeedback(orderId, feedback);
    }

    @Override
    public void undo() {
        feedbackReceiver.undoFeedback(orderId);
    }
}

