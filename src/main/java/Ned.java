import java.util.Arrays;
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
        return (Pattern.matches("mark [0-9]+\\s*$", input));
    };
    private static boolean isUnMarkCommand(String input) {
        return (Pattern.matches("unmark [0-9]+\\s*$", input));
    };
    private static boolean isToDoCommand(String input) {
        return (Pattern.matches("todo .+\\s*$", input));
    };
    private static boolean isDeadlineCommand(String input) {
        return (Pattern.matches("deadline .+ /by .+\\s*$", input));
    };

    private static boolean isEventCommand(String input) {
        return (Pattern.matches("event .+ /from .+ /to .+\\s*$", input));
    };

    private static boolean isTaskCommandType(String input) {
        return isToDoCommand(input) || isDeadlineCommand(input) || isEventCommand(input);
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
            } else if (isMarkCommand(nextInput)) {
                executeMarkOrUnmarkCommand(true, nextInput);
            } else if (isUnMarkCommand(nextInput)) {
                executeMarkOrUnmarkCommand(false, nextInput);
            } else if (isTaskCommandType(nextInput)) {
                Task newTask;
                if (isToDoCommand(nextInput)) {
                    String[] parsed_inputs = nextInput.split("todo", 2);
                    //the string is split at most limit - 1 times
//                    System.out.println(Arrays.toString(parsed_inputs));
                    newTask = Task.createTask(parsed_inputs[1].strip());
                } else if (isDeadlineCommand(nextInput)) {
                    String[] parsed_inputs = nextInput.split("deadline|/by", 3);
//                    System.out.println(Arrays.toString(parsed_inputs));
                    newTask = Task.createTask(parsed_inputs[1].strip(), parsed_inputs[2].strip());
                } else {
                    String[] parsed_inputs = nextInput.split("event|/from|/to", 4);
                    newTask = Task.createTask(parsed_inputs[1].strip(), parsed_inputs[2].strip(), parsed_inputs[3].strip());
                };
                Ned.listOfText.add(newTask);
                print("____________________________________________________________\n");
                print("Aye, I've added this task m'lord:");
                print(Ned.indentations  + newTask);
                print(String.format("Now you've %d tasks in the list. Get to it then.", Ned.listOfText.size()));
                print("____________________________________________________________\n");
            } else {
                System.out.println(Ned.indentations + "____________________________________________________________\n");
                System.out.println(Ned.indentations + "added: " + nextInput + "\n");
                Task newTask = Task.createTask(nextInput); //creates a ToDo by default
                Ned.listOfText.add(newTask);
                System.out.println(Ned.indentations + "____________________________________________________________\n");
            }
        }
    };
}
