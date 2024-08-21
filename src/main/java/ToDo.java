import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class ToDo extends Task {

    protected ToDo(String taskDescription) {
        super(taskDescription);
        this.taskType = "T";
    };

    public static void addTask(String input, ArrayList<Task> listOfText) throws NedException {
        String[] parsed_inputs = input.split("todo", 2);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a todo task with no description");
        };
        Task newTask = new ToDo(parsed_inputs[1].strip());
        listOfText.add(newTask);
        Ned.print("Aye, I've added this task m'lord:");
        Ned.print(Ned.indentations + newTask);
    };
};
