import java.util.ArrayList;
import java.util.regex.Pattern;

public class CommandManager {

    private final static String MarkRegex = "^mark.*";
    private final static String UnmarkRegex = "^unmark.*";

    private final static String TodoRegex = "^todo.*";

    private final static String DeadlineRegex = "^deadline.*";

    private final static String EventRegex = "^event.*";

    private final static String DeleteRegex = "^delete.*";

    private final static String ListFlag = "list";
    private static final String ByeFlag = "bye";
    private final ArrayList<Task> listOfText;

    public final static String commandMessage = """
            Usage: 
                list                             - Shows the list of all tasks 
                mark <item-index>                - Marks the item at the specified index as done
                unmark <item-index>              - Marks the item at the specified index as undone 
                delete <item-index>              - Removes the item at the specified index from the list            
                todo <description>               - Creates a new todo task and adds it to the list
                deadline <description> /by <date> - Creates a new deadline task and adds it to the list
                event <description> /from <date> /to <date> - Creates a new event task and adds it to the list""";
    public CommandManager(ArrayList<Task> listOfText) {
        this.listOfText = listOfText;
    };

    public void checkForCommands (String input, FlagWrapper flag) {
        //checks whether the input fits into all existing command types known
        if (!(checkForMarkCommands(input) || checkForTaskCommands(input) || checkForListCommand(input) || checkForByeCommand(input, flag) || checkForDeleteCommand(input))) {
            Ned.print("M'lord, you seem to have given me a nonsensical command. Input a correct command, for we have little time!");
            Ned.print("Winter is coming...");
        };
    }
    private boolean checkForMarkCommands(String input) {
        //will check if command is mark or unmark and execute accordingly
        if (isMarkCommand(input)) {
            new MarkAndUnmarkCommand(true).executeMarkCommand(input, this.listOfText);
        } else if (isUnmarkCommand(input)){
            new MarkAndUnmarkCommand(false).executeMarkCommand(input, this.listOfText);
        } else {
            return false;
        }
        return true;
    };

    private boolean checkForTaskCommands(String input) {
        Task newTask;
        try {
            if (isTodoTask(input)) {
                newTask = ToDo.createTask(input);
            } else if (isDeadlineTask(input)) {
                newTask = Deadline.createTask(input);
            } else if (isEventTask(input)) {
                newTask = Event.createTask(input);
            } else {
                return false;
            }
        } catch (NedException e) {
            Ned.print(e.getMessage());
            Ned.print(CommandManager.commandMessage);
            return true;
        };
        this.listOfText.add(newTask);
        Ned.print("Aye, I've added this task m'lord:");
        Ned.print(Ned.indentations  + newTask);
        return true;
    };

    private boolean checkForByeCommand(String input, FlagWrapper flag) {
        if (isByeCommand(input)) {
            flag.setStatus(false);
            return true;
        };
        return false;
    };

    private boolean checkForListCommand(String input) {
        if (isListCommand(input)) {
            new ListCommand().executeListCommand(this.listOfText);
            return true;
        };
        return false;
    };

    private boolean checkForDeleteCommand(String input) {
        if (isDeleteCommand(input)) {
            new DeleteCommand(input).executeDeleteCommand(this.listOfText);
            return true;
        };
        return false;
    };

    private boolean isListCommand(String input) {
        return input.equalsIgnoreCase(CommandManager.ListFlag);
    };
    private boolean isByeCommand(String input) {
        return input.equalsIgnoreCase(CommandManager.ByeFlag);
    };

    private boolean isDeleteCommand(String input) {
        return Pattern.matches(DeleteRegex, input);
    };
    private boolean isMarkCommand(String input) {
        return Pattern.matches(MarkRegex, input);
    };
    private boolean isUnmarkCommand(String input) {
        return Pattern.matches(UnmarkRegex, input);
    };

    private boolean isTodoTask(String input) {
        return Pattern.matches(TodoRegex, input);
    };

    private boolean isDeadlineTask(String input) {
        return Pattern.matches(DeadlineRegex, input);
    };

    private boolean isEventTask(String input) {
        return Pattern.matches(EventRegex, input);
    };

}
