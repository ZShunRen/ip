import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        Ned.loadInSavedTasks("cachedTasks.txt");
        Ned.checkCommands();
        Ned.showByeMessage();
    }

    public static void showWelcomeMessage() {
        print("Hello! I'm\n" + logo + "\n");
        print("Lord of Winterfell and Warden Of The North");
        print("What can I do for you?");
    }


    public static void loadInSavedTasks(String cacheFilePath) {
        //loads in the tasks saved as csv : e.g. ("todo", "Read") and ("deadline", "read", "monday 2pm") etc
        try {
            File f = new File(cacheFilePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()){
                Task newTask = CommandManager.parseSavedTask(s.nextLine());
                if (newTask != null) {
                    Ned.listOfText.add(newTask);
                };
            }
        } catch (FileNotFoundException e) {
            print("M'lord, do not be alarmed, but it appears that there was no previous saved task file. Not to " +
                    "worry, we'll sort this out yet...");
        }
    }

    public static void showByeMessage() {
        print("I wish you good fortune in the wars to come, m' lord");
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
