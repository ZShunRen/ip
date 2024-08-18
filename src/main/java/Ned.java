import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Ned {
    private static final String byeflag = "bye";
    private static final String indentations = "    ";

    private static final ArrayList<Task> listOfText= new ArrayList<>();
    private static final String logo = Ned.indentations + " ____  _____              __  \n"
                + Ned.indentations + "|_   \\|_   _|            |  ] \n"
                + Ned.indentations + "  |   \\ | |  .---.   .--.| |  \n"
                + Ned.indentations + "  | |\\ \\| | / /__\\\\/ /'`\\' |  \n"
                + Ned.indentations + " _| |_\\   |_| \\__.,| \\__/  |  \n"
                + Ned.indentations + "|_____|\\____|'.__.' '.__.;__]";
    public static void main(String[] args) {
        Ned.welcomeMessage();
        Ned.addCommands();
        Ned.byeMessage();
    }

    public static void welcomeMessage() {
        System.out.println(Ned.indentations + "____________________________________________________________\n");
        System.out.println(Ned.indentations + "Hello! I'm\n" + logo + "\n");
        System.out.println(Ned.indentations +  "Lord of Winterfell and Warden Of The North\n");
        System.out.println(Ned.indentations + "What can I do for you?");
    };

    public static void byeMessage() {
        System.out.println(Ned.indentations + "____________________________________________________________\n");
        System.out.println(Ned.indentations + "I wish you good fortune in the wars to come, m' lord\n");
        System.out.println(Ned.indentations + "____________________________________________________________\n");
    }
    public static void print(String line) {
        //adds indentation to any printed lines
        System.out.println(Ned.indentations + line);
    }

    private static boolean isMarkCommand(String input) {
        //if it is, it executes the command
        if (Pattern.matches("mark [0-9]+\\s*$", input)) {
            executeMarkOrUnmarkCommand(true, input);
            return true;
        } else if (Pattern.matches("unmark [0-9]+\\s*$", input)) {
            executeMarkOrUnmarkCommand(false, input);
            return true;
        } else {
            return false;
        }
    };
    private static void executeMarkOrUnmarkCommand(boolean isMarkCommand, String input) {
        String[] words = input.split(" ");
        String possibleIndex = words[1];
        try {
            int index = Integer.parseInt(possibleIndex);
            Task selectedTask = Ned.listOfText.get(index - 1);
            if (isMarkCommand) {
                selectedTask.markAsDone();
                print("Good work. One down, more to go!");
            } else {
                selectedTask.markAsNotDone();
                print("Oh no. One back up, more to go!");
            };
            print(selectedTask.toString());
        } catch (PatternSyntaxException e) {
            print("____________________________________________________________\n");
            print("Sorry m'lord, seems there was a typo in the command try again.");
            print("____________________________________________________________\n");
        } catch (NumberFormatException e) {
            print("____________________________________________________________\n");
            print("Sorry m'lord, seems you did not specify a valid number");
            print("____________________________________________________________\n");
        } catch (IndexOutOfBoundsException e) {
            print("____________________________________________________________\n");
            print("Sorry m'lord, seems the item number you specified is not valid");
            print("____________________________________________________________\n");
        }
    }
    public static void addCommands() {
        print("____________________________________________________________\n");
        System.out.println("\n");
        Scanner inputDetector = new Scanner(System.in);
        while (true) {
            String nextInput = inputDetector.nextLine();
            if (nextInput.equalsIgnoreCase(Ned.byeflag)) {
                break;
            } else if (nextInput.equalsIgnoreCase("list")) {
                print("____________________________________________________________\n");
                for (int i = 0; i < Ned.listOfText.size(); i++) {
                    String task = Ned.indentations + String.format("%d.%s \n", i + 1, Ned.listOfText.get(i));
                    print(task);
                };
                print("____________________________________________________________\n");
            } else if (!isMarkCommand(nextInput)) {
                System.out.println(Ned.indentations + "____________________________________________________________\n");
                System.out.println(Ned.indentations + "added: " + nextInput + "\n");
                Task newTask = new Task(nextInput);
                Ned.listOfText.add(newTask);
                System.out.println(Ned.indentations + "____________________________________________________________\n");
            }
        }
    };
}
