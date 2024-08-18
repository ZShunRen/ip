public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    };

    public void markAsDone() {
        this.isDone = true;
    };

    public void markAsNotDone() {
        this.isDone = false;
    };

    @Override
    public String toString() {
        String marker = this.isDone ? "X" : " ";
        return String.format("[%s] %s", marker, this.taskName);
    };
}
