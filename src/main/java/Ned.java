import java.util.Scanner;
import java.util.ArrayList;
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
        Ned.checkCommands();
        Ned.byeMessage();
    }

    public static void welcomeMessage() {
        System.out.println(Ned.indentations + "Hello! I'm\n" + logo + "\n");
        System.out.println(Ned.indentations +  "Lord of Winterfell and Warden Of The North\n");
        System.out.println(Ned.indentations + "What can I do for you?");
    };

    public static void byeMessage() {
        System.out.println(Ned.indentations + "I wish you good fortune in the wars to come, m' lord\n");
    }
    public static void print(String line) {
        //adds indentation to any printed lines
        System.out.println(Ned.indentations + line);
    }
    private static boolean isMarkCommand(String input) {
        //if it is, it executes the command
        return (Pattern.matches("mark.*", input));
    };
    private static boolean isUnMarkCommand(String input) {
        return (Pattern.matches("unmark.*", input));
    };
    private static boolean isToDoCommand(String input) {
        return (Pattern.matches("todo.*", input));
    };
    private static boolean isDeadlineCommand(String input) {
        return (Pattern.matches("deadline.*", input));
    };

    private static boolean isEventCommand(String input) {
        return (Pattern.matches("event.*", input));
    };

    private static boolean isTaskCommandType(String input) {
        return isToDoCommand(input) || isDeadlineCommand(input) || isEventCommand(input);
    };
    private static boolean isDeleteCommand(String input) {
        //if it is, it executes the command
        return (Pattern.matches("delete.*", input));
    };

    private static int getTaskCommandType(String input) {
        if (isToDoCommand(input)) return 1;
        else if (isDeadlineCommand(input)) return 2;
        else if (isEventCommand(input)) return 3;
        else return -1; //for event commands
    };
    private static int checkSizeOfInput(String[] strArr) {
        int count = 0;
        for (String s : strArr) {
            if (!s.isBlank())  {
                count += 1;
            }
        }
        ;
        return count;
    }
    private static void executeTaskCommand(String input) throws NedException {
        Task newTask;
        String[] parsed_inputs;
        newTask = switch (getTaskCommandType(input)) {
            case 1 -> {
                parsed_inputs = input.split("todo", 2);
                if (parsed_inputs[1].strip().isBlank()) {
                    throw new NedException("M'lord, you cannot create a todo task with no description");
                };
                yield Task.createTask(parsed_inputs[1].strip());
            }
            case 2 -> {
                parsed_inputs = input.split("deadline|/by", 3);
                int parsed_inputs_len = checkSizeOfInput(parsed_inputs);
                if (parsed_inputs[1].strip().isBlank()) {
                    throw new NedException("M'lord, you cannot create a deadline task with no description");
                } else if (parsed_inputs_len == 1) {
                    throw new NedException("M'lord, you cannot create a deadline task with no due date");
                };
                yield Task.createTask(parsed_inputs[1].strip(), parsed_inputs[2].strip());
            }
            case 3 -> {
                parsed_inputs = input.split("event|/from|/to", 4);
                int parsed_inputs_len = checkSizeOfInput(parsed_inputs);
                if (parsed_inputs[1].strip().isBlank()) {
                    throw new NedException("M'lord, you cannot create an event task with no description");
                } else if (parsed_inputs_len <= 2 ) {
                    throw new NedException("M'lord, you cannot create an event task with no 'from' date " +
                            "or no 'to' date. Gods be good, fill both up!");
                };
                yield Task.createTask(parsed_inputs[1].strip(), parsed_inputs[2].strip(), parsed_inputs[3].strip());
            }
            //unchecked exception thrown, does not have to be caught
            default -> throw new IllegalStateException("Unexpected task command: " + getTaskCommandType(input));
        };
        Ned.listOfText.add(newTask);
        print("Aye, I've added this task m'lord:");
        print(Ned.indentations  + newTask);
        print(String.format("Now you've %d tasks in the list. Get to it then.", Ned.listOfText.size()));
    };
    private static void executeMarkOrUnmarkCommand(boolean isMarkCommand, String input) throws NedException {
        String[] words = input.split(" ");
        if (words.length != 2) {
            throw new NedException("Sorry m'lord, you must give me a list index with the mark/unmark command. No more, no less");
        };
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
            print("Sorry m'lord, seems there was a typo in the command try again.");
        } catch (NumberFormatException e) {
            //never executed because of regex
            print("Sorry m'lord, your command must specify a valid number");
        } catch (IndexOutOfBoundsException e) {
            print("Sorry m'lord, seems the item number you specified is not valid");
        }
    };
    private static void executeDeleteCommand(String input) throws NedException {
        String[] words = input.split(" ");
        if (words.length != 2) {
            throw new NedException("Sorry m'lord, you must give me a list index with the delete command. No more, no less");
        } else {
            String possibleIndex = words[1];
            try {
                int index = Integer.parseInt(possibleIndex) - 1;
                Task selectedTask = Ned.listOfText.get(index);
                print("Noted m'lord. The following task has been removed:\n");
                print(Ned.indentations + selectedTask);
                Ned.listOfText.remove(index); //removes the index specified
                print(String.format("Now you've %d tasks in the list. Get to it then.", Ned.listOfText.size()));
            } catch (NumberFormatException e) {
                print("Sorry m'lord, your command must specify a valid number");
            } catch (IndexOutOfBoundsException e) {
                print("Sorry m'lord, seems the item number you specified is not valid");
            }
        }
    };
    private static void checkCommands() {
        System.out.println("\n");
        Scanner inputDetector = new Scanner(System.in);
        while (true) {
            String nextInput = inputDetector.nextLine();
            if (nextInput.equalsIgnoreCase(Ned.byeflag)) {
                break;
            } else if (nextInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < Ned.listOfText.size(); i++) {
                    String task = Ned.indentations + String.format("%d.%s \n", i + 1, Ned.listOfText.get(i));
                    print(task);
                };
            } else if (isMarkCommand(nextInput)) {
                try {
                    executeMarkOrUnmarkCommand(true, nextInput);
                } catch (NedException e) {
                    print(e.getMessage());
                }
            } else if (isUnMarkCommand(nextInput)) {
                try {
                    executeMarkOrUnmarkCommand(false, nextInput);
                } catch (NedException e) {
                    print(e.getMessage());
                }
            } else if (isTaskCommandType(nextInput)) {
                try {
                    executeTaskCommand(nextInput);
                } catch (NedException e) {
                    print(e.getMessage());
                }
            } else if (isDeleteCommand(nextInput)) {
                try {
                    executeDeleteCommand(nextInput);
                } catch (NedException e) {
                    print(e.getMessage());
                }
            } else {
                print("M'lord, you seem to have given me a nonsensical command. Input a correct command, for we have little time!");
                print("Winter is coming...");
            }
        }
    };
}
