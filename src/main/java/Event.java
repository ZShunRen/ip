public class Event extends Task {
    private String fromTiming;
    private String toTiming;
    public Event(String taskDescription, String fromTiming, String toTiming) {
        super(taskDescription);
        this.fromTiming = fromTiming;
        this.toTiming = toTiming;
        this.taskType = "E";
    };

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),this.fromTiming, this.toTiming);
    };
};
