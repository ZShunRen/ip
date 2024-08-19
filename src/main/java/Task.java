public abstract class Task {
    private boolean isDone;
    private String taskDescription;

    protected String taskType;

    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    };

    public static Task createTask(String taskDescription) {
        return new ToDo(taskDescription);
    };

    public static Task createTask(String taskDescription, String deadlineTiming) {
        return new Deadline(taskDescription, deadlineTiming);
    };

    public static Task createTask(String taskDescription, String fromTiming, String toTiming) {
        return new Event(taskDescription, fromTiming, toTiming);
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
        return String.format("[%s][%s] %s",this.taskType, marker, this.taskDescription);
    };
}
