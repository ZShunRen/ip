public abstract class Task {
    private boolean isDone;
    private String taskDescription;

    protected String taskType;

    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    };

    public void markAsDone() {
        this.isDone = true;
    };

    public void markAsNotDone() {
        this.isDone = false;
    };

    protected static int checkSizeOfInput(String[] strArr) {
        int count = 0;
        for (String s : strArr) {
            if (!s.isBlank())  {
                count += 1;
            }
        }
        ;
        return count;
    };
    @Override
    public String toString() {
        String marker = this.isDone ? "X" : " ";
        return String.format("[%s][%s] %s",this.taskType, marker, this.taskDescription);
    };
}
