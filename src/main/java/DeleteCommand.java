import java.util.ArrayList;

public class DeleteCommand {
    private final String commandInput;

    public DeleteCommand(String commandInput) {
        this.commandInput = commandInput;
    }

    public void executeDeleteCommand(ArrayList<Task> listOfText) {
        String[] words = this.commandInput.split(" ");
        try {
            if (words.length != 2) {
                throw new NedException("Sorry m'lord, you must give me a list index with the delete command. No more, no less");
            } else {
                String possibleIndex = words[1];
                int index = Integer.parseInt(possibleIndex) - 1;
                Task selectedTask = listOfText.get(index);
                Ned.print("Noted m'lord. The following task has been removed:\n");
                Ned.print(Ned.INDENTATIONS + selectedTask);
                listOfText.remove(index); //removes the index specified
                Ned.print(String.format("Now you've %d tasks in the list. Get to it then.", listOfText.size()));
            }
            ;
        } catch (NumberFormatException e) {
            Ned.print("Sorry m'lord, your command must specify a valid number");
            Ned.print(CommandManager.COMMAND_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            Ned.print("Sorry m'lord, seems the item number you specified is not valid");
        } catch (NedException e) {
            Ned.print(e.getMessage());
            Ned.print(CommandManager.COMMAND_MESSAGE);
        }
    }
}
