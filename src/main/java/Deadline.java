public class Deadline extends Task {
    public String deadlineTiming;

    public Deadline(String taskDescription, String deadlineTiming) {
        super(taskDescription);
        this.deadlineTiming = deadlineTiming;
        this.taskType = "D";
    };
    @Override
    public String toString() {
        return super.toString() + " " + String.format("(by: %s)", this.deadlineTiming);
    };
}
