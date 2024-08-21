import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class ToDo extends Task {

    protected ToDo(String taskDescription) {
        super(taskDescription);
        this.taskType = "T";
    };

    public static Task createTask(String input) throws NedException {
        String[] parsed_inputs = input.split("todo", 2);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a todo task with no description");
        };
        return new ToDo(parsed_inputs[1].strip());
    };
}
