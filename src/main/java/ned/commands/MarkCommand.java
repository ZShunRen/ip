package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;
import ned.exceptions.NedException;

public class MarkCommand implements Command {

    private final String REGEX = "^mark.*";
    public MarkCommand() {}

    /**
     * Marks a task in the list of tasks as done
     * @param taskList An object which contains the ArrayList that stores the list of tasks. In addition, also
     *                 handles modifications and reading from the ArrayList
     * @param uiInstance An object which handles output that is displayed to users and input from users
     * @param storageInstance An object which handles loading in and modifications to the cached list of tasks
     * @param userInput A string which represents input from users into Ned
     * @throws NedException Thrown if the index is not a number or a valid index
     */
    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException {
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            throw new NedException("Sorry m'lord, you must give me a list index with the mark command. No more, no " +
                    "less" + uiInstance.getCommandMessage());
        }
        String possibleIndex = words[1];
        try {
            int index = Integer.parseInt(possibleIndex) - 1;
            taskList.markTaskAsDone(index, uiInstance);
            storageInstance.save(taskList);
        } catch (NumberFormatException e) {
            throw new NedException("Sorry m'lord, your command must specify a valid number" + uiInstance.getCommandMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, seems the item number you specified is not valid" + uiInstance.getCommandMessage());
        }
    }

    /**
     * Returns the regex expression used to identify the mark command
     * Used in Parser class to find the associated command
     * @return The regex pattern associated with this command
     */
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
