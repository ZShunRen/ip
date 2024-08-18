import java.util.Scanner;

public class Ned {
    private static final String byeflag = "bye";
    private static final String indentations = "    ";
    private static final String logo = Ned.indentations + " ____  _____              __  \n"
                + Ned.indentations + "|_   \\|_   _|            |  ] \n"
                + Ned.indentations + "  |   \\ | |  .---.   .--.| |  \n"
                + Ned.indentations + "  | |\\ \\| | / /__\\\\/ /'`\\' |  \n"
                + Ned.indentations + " _| |_\\   |_| \\__.,| \\__/  |  \n"
                + Ned.indentations + "|_____|\\____|'.__.' '.__.;__]";
    public static void main(String[] args) {
        Ned.welcomeMessage();
        Ned.echoCommands();
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
    public static void echoCommands() {
        System.out.println(Ned.indentations + "____________________________________________________________\n");
        System.out.println("\n");
        Scanner inputDetector = new Scanner(System.in);
        while (true) {
            String nextInput = inputDetector.nextLine();
            if (nextInput.equalsIgnoreCase(Ned.byeflag)) {
                break;
            } else {
                System.out.println(Ned.indentations + "____________________________________________________________\n");
                System.out.println(Ned.indentations + nextInput + "\n");
                System.out.println(Ned.indentations + "____________________________________________________________\n");
            }
        }
    };
}
