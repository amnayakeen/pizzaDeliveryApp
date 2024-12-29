import java.util.Stack;

class FeedbackInvoker {
    private Command command;
    private final Stack<Command> commandHistory = new Stack<>();

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command != null) {
            command.execute();
            commandHistory.push(command);
        } else {
            System.out.println("No command is set!");
        }
    }

    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("No commands to undo!");
        }
    }
}
