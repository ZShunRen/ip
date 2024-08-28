package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.exceptions.NedException;
import ned.Ui;
import ned.tasks.Task;
import ned.tasks.Deadline;

public class AddDeadlineCommand implements Command {
    private final String REGEX = "^deadline.*";

    /**
     * This method carries out the adding of a new Deadline object with information parsed from the command to the
     * taskList of
     * the Ned instance's tasklist
     * The NedException is thrown if the user does not specify a description for the task
     * and if there is no deadline specified in the command using /by
     *
     * @param taskList        The TaskList instance associated with the Ned instance, used to store the list of tasks and
     *                        to modify them
     * @param uiInstance      The Ui instance associated with the Ned instance, used to display user output, and take in
     *                        user input
     * @param storageInstance The storage instance associated with the Ned instance, used to load in saved tasks
     *                        from the cache file as well as to modify the cache file when the list of tasks are changed
     * @param userInput       The string of user input, is parsed for relevant information needed to execute commands
     * @throws NedException A generic exception associated with the Ned class. It is caught and its message shown
     *                      to the user
     */
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException {
        String[] parsed_inputs = userInput.split("deadline|/by", 3);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a deadline task with no description" + uiInstance.getCommandMessage());
        } else if (parsed_inputs_len == 1) {
            throw new NedException("M'lord, you cannot create a deadline task with no due date" + uiInstance.getCommandMessage());
        }
        Task newTask = Deadline.createDeadline(parsed_inputs[1].strip(), parsed_inputs[2].strip(), false);
        taskList.addTask(newTask, uiInstance);
        storageInstance.save(taskList);
    }

    /**
     * Returns the regex expression used to identify the deadline command
     * Used in Parser class to find the associated command
     *
     * @return The regex pattern associated with this command
     */
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
