import java.util.ArrayList;
import java.util.regex.Pattern;

public class CommandManager {

    private final static String REGEX_MARK = "^mark.*";
    private final static String REGEX_UNMARK = "^unmark.*";

    private final static String REGEX_TODO = "^todo.*";

    private final static String REGEX_DEADLINE = "^deadline.*";

    private final static String REGEX_EVENT = "^event.*";

    private final static String REGEX_DELETE = "^delete.*";

    private final static String FLAG_LIST = "list";
    private static final String FLAG_BYE = "bye";
    private final ArrayList<Task> listOfText;

    private enum CommandTypes {
        MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, LIST, BYE, UNKNOWN
    }

    public final static String COMMAND_MESSAGE = """
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
    }

    public void processCommand(String input, FlagWrapper flag) {
        CommandTypes command = checkForCommands(input);
        try {
            switch (command) {
            case MARK:
                new MarkAndUnmarkCommand(true).executeMarkCommand(input, this.listOfText);
                break;
            case UNMARK:
                new MarkAndUnmarkCommand(false).executeMarkCommand(input, this.listOfText);
                break;
            case TODO:
                ToDo.addTask(input, this.listOfText);
                break;
            case DEADLINE:
                Deadline.addTask(input, this.listOfText);
                break;
            case EVENT:
                Event.addTask(input, this.listOfText);
                break;
            case DELETE:
                new DeleteCommand(input).executeDeleteCommand(this.listOfText);
                break;
            case LIST:
                new ListCommand().executeListCommand(this.listOfText);
                break;
            case BYE:
                flag.setStatus(false);
                break;
            case UNKNOWN:
            default:
                throw new NedException("M'lord, you seem to have given me a nonsensical command." +
                        " Input a correct command, for we have little time! Winter is coming....");
            }

        } catch (NedException e) {
            Ned.print(e.getMessage());
            Ned.print(CommandManager.COMMAND_MESSAGE);
        }
    }

    private CommandTypes checkForCommands(String input) {
        //checks whether the input fits into all existing command types known
        if (isMarkCommand(input)) return CommandTypes.MARK;
        if (isUnmarkCommand(input)) return CommandTypes.UNMARK;
        if (isListCommand(input)) return CommandTypes.LIST;
        if (isByeCommand(input)) return CommandTypes.BYE;
        if (isDeleteCommand(input)) return CommandTypes.DELETE;
        if (isTodoTask(input)) return CommandTypes.TODO;
        if (isDeadlineTask(input)) return CommandTypes.DEADLINE;
        if (isEventTask(input)) return CommandTypes.EVENT;
        else return CommandTypes.UNKNOWN;
    }

    private boolean isListCommand(String input) {
        return input.equalsIgnoreCase(CommandManager.FLAG_LIST);
    }

    private boolean isByeCommand(String input) {
        return input.equalsIgnoreCase(CommandManager.FLAG_BYE);
    }

    private boolean isDeleteCommand(String input) {
        return Pattern.matches(REGEX_DELETE, input);
    }

    private boolean isMarkCommand(String input) {
        return Pattern.matches(REGEX_MARK, input);
    }

    private boolean isUnmarkCommand(String input) {
        return Pattern.matches(REGEX_UNMARK, input);
    }

    private boolean isTodoTask(String input) {
        return Pattern.matches(REGEX_TODO, input);
    }

    private boolean isDeadlineTask(String input) {
        return Pattern.matches(REGEX_DEADLINE, input);
    }

    private boolean isEventTask(String input) {
        return Pattern.matches(REGEX_EVENT, input);
    }
}
