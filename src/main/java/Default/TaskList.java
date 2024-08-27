package Default;

import java.util.ArrayList;

import Exceptions.NedException;
import Tasks.Task;
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void removeTask(int index, Ui uiInstance) throws NedException {
        try {
            Task selectedTask = getTask(index);
            this.listOfTasks.remove(index);
            uiInstance.print("Noted m'lord. The following task has been removed:");
            uiInstance.print(Ui.INDENTATIONS + selectedTask);
            uiInstance.print(String.format("Now you've %d tasks in the list. Get to it then.", listOfTasks.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, your command must specify an index within the bounds of the list " +
                    "size");
        }
    }

    private Task getTask(int index) throws NedException {
        try {
            return this.listOfTasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, your command must specify an index within the bounds of the list " +
                    "size");
        }
    }
    public void addTask(Task newTask, Ui uiInstance){
        this.listOfTasks.add(newTask);
        uiInstance.print("Aye, I've added this task m'lord:");
        uiInstance.print(Ui.INDENTATIONS + newTask);
        uiInstance.print("Now you've " + this.listOfTasks.size() + " tasks left. Get to it then!");
    }

    public void listTasks(Ui uiInstance) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            String task = Ui.INDENTATIONS + String.format("%d.%s \n", i + 1, listOfTasks.get(i));
            uiInstance.print(task);
        }
    }

    public void markTaskAsDone(int index, Ui uiInstance) throws NedException{
        Task selectedTask = getTask(index);
        selectedTask.markAsDone();
        uiInstance.print("Good work. One down, more to go!");
        uiInstance.print(selectedTask.toString());
    }
    public void markTaskAsUndone(int index, Ui uiInstance) throws NedException{
        Task selectedTask = getTask(index);
        selectedTask.markAsNotDone();
        uiInstance.print("Oh no. One back up, more to go!");
        uiInstance.print(selectedTask.toString());
    }

    public int getSize() {
        return this.listOfTasks.size();
    }

    public String getTaskTextForm(int index) throws NedException{
        return getTask(index).toTextForm();
    }


}
