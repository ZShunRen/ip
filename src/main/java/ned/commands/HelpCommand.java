package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;

/**
 * The {@code HelpCommand} class implements the {@link Command} interface
 * and provides a way to display help information to the user.
 * This command matches the "help" input and responds by providing the user with command usage information.
 */
public class HelpCommand implements Command {
    /**
     * The regular expression pattern that this command matches.
     */
    private static final String REGEX = "help";

    /**
     * Executes the help command.
     * This method adds a message to the UI instance that contains information about how to use the available commands.
     *
     * @param taskList The current {@link TaskList}, though this command does not interact with it.
     * @param uiInstance The {@link Ui} instance used to display messages to the user.
     * @param storageInstance The {@link Storage} instance used for data handling, though not used in this command.
     * @param userInput The user's input, which is expected to be "help" to trigger this command.
     */
    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) {
        uiInstance.addToNedDialogue(uiInstance.getCommandMessage());
    }

    /**
     * Returns the regular expression pattern that this command matches.
     *
     * @return The regular expression pattern as a {@link String}.
     */
    @Override
    public String getRegex() {
        return REGEX;
    }
}
