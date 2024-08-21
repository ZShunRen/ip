import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class MarkAndUnmarkCommand {

    private final boolean isMarkCommand;

    public MarkAndUnmarkCommand(boolean isMarkCommand) {
        this.isMarkCommand = isMarkCommand;
    };
    public void executeMarkCommand(String input, ArrayList<Task> listOfText) {
        String[] words = input.split(" ");
        try {
            if (words.length != 2) {
                throw new NedException("Sorry m'lord, you must give me a list index with the mark/unmark command. No more, no less");
            }
            ;
            String possibleIndex = words[1];
            try {
                int index = Integer.parseInt(possibleIndex);
                Task selectedTask = listOfText.get(index - 1);
                if (this.isMarkCommand) {
                    selectedTask.markAsDone();
                    Ned.print("Good work. One down, more to go!");
                } else {
                    selectedTask.markAsNotDone();
                    Ned.print("Oh no. One back up, more to go!");
                }
                Ned.print(selectedTask.toString());
            } catch (PatternSyntaxException e) {
                Ned.print("Sorry m'lord, seems there was a typo in the command try again.");
                Ned.print(CommandManager.commandMessage);
            } catch (NumberFormatException e) {
                //never executed because of regex
                Ned.print("Sorry m'lord, your command must specify a valid number");
                Ned.print(CommandManager.commandMessage);
            } catch (IndexOutOfBoundsException e) {
                Ned.print("Sorry m'lord, seems the item number you specified is not valid");
                Ned.print(CommandManager.commandMessage);
            }
        } catch (NedException e) {
            Ned.print(e.getMessage());
        }
    };
};
