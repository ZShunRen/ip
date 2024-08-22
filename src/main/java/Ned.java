import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Ned {
    public static final String INDENTATIONS = "    ";

    private static final ArrayList<Task> listOfText = new ArrayList<>();
    private static final String logo = Ned.INDENTATIONS + " ____  _____              __  \n"
            + Ned.INDENTATIONS + "|_   \\|_   _|            |  ] \n"
            + Ned.INDENTATIONS + "  |   \\ | |  .---.   .--.| |  \n"
            + Ned.INDENTATIONS + "  | |\\ \\| | / /__\\\\/ /'`\\' |  \n"
            + Ned.INDENTATIONS + " _| |_\\   |_| \\__.,| \\__/  |  \n"
            + Ned.INDENTATIONS + "|_____|\\____|'.__.' '.__.;__]";

    public static void main(String[] args) {
        Ned.showWelcomeMessage();
        Ned.checkCommands();
        Ned.showByeMessage();
    }

    public static void showWelcomeMessage() {
        print("Hello! I'm\n" + logo + "\n");
        print("Lord of Winterfell and Warden Of The North\n");
        print("What can I do for you?");
    }

    public static void showByeMessage() {
        print("I wish you good fortune in the wars to come, m' lord\n");
    }

    public static void print(String line) {
        //adds indentation to any printed lines
        System.out.println(Ned.INDENTATIONS + line);
    }

    private static void checkCommands() {
        System.out.println("\n");
        CommandManager commandParser = new CommandManager(Ned.listOfText);
        Scanner inputDetector = new Scanner(System.in);
        FlagWrapper flag = new FlagWrapper(true);
        while (flag.getStatus()) {
            String nextInput = inputDetector.nextLine();
            commandParser.processCommand(nextInput, flag);
        }
    }
}
