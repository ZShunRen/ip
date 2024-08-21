public class Deadline extends Task {
    public String deadlineTiming;

    protected Deadline(String taskDescription, String deadlineTiming) {
        super(taskDescription);
        this.deadlineTiming = deadlineTiming;
        this.taskType = "D";
    };
    @Override
    public String toString() {
        return super.toString() + " " + String.format("(by: %s)", this.deadlineTiming);
    };
    public static Task createTask(String input) throws NedException{
        String[] parsed_inputs = input.split("deadline|/by", 3);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a deadline task with no description");
        } else if (parsed_inputs_len == 1) {
            throw new NedException("M'lord, you cannot create a deadline task with no due date");
        };
        return new Deadline(parsed_inputs[1].strip(), parsed_inputs[2].strip());
    };
}
