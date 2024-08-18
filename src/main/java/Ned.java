import java.util.Scanner;
import java.util.ArrayList;
public class Ned {
    private static final String byeflag = "bye";
    private static final String indentations = "    ";

    private static final ArrayList<String> listOfText= new ArrayList<>();
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
    public static void addCommands() {
        System.out.println(Ned.indentations + "____________________________________________________________\n");
        System.out.println("\n");
        Scanner inputDetector = new Scanner(System.in);
        while (true) {
            String nextInput = inputDetector.nextLine();
            if (nextInput.equalsIgnoreCase(Ned.byeflag)) {
                break;
            } else if (nextInput.equalsIgnoreCase("list")) {
                System.out.println(Ned.indentations + "____________________________________________________________\n");
                for (int i = 0; i < Ned.listOfText.size(); i++) {
                    String task = Ned.indentations + String.format("%d. %s \n", i + 1, Ned.listOfText.get(i));
                    System.out.println(task);
                };
                System.out.println(Ned.indentations + "____________________________________________________________\n");
            } else {
                System.out.println(Ned.indentations + "____________________________________________________________\n");
                System.out.println(Ned.indentations + "added: " + nextInput + "\n");
                Ned.listOfText.add(nextInput);
                System.out.println(Ned.indentations + "____________________________________________________________\n");
            }
        }
    };
}
