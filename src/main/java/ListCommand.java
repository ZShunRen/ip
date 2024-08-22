import java.util.ArrayList;

public class ListCommand {
    public ListCommand() {};

    public void executeListCommand(ArrayList<Task> listOfText) {
        for (int i = 0; i < listOfText.size(); i++) {
            String task = Ned.INDENTATIONS + String.format("%d.%s \n", i + 1, listOfText.get(i));
            Ned.print(task);
        };
    }
}
