import java.util.Scanner;
import java.util.ArrayList;

public class Steve {
    private static String divider = "----------";

    public static void main(String[] args) {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> userList = new ArrayList<>();


        while (true) {
            String inpt = sc.nextLine();
            if (inpt.equals("bye")) {
                break;
            }
            
            if (inpt.equals(("list"))) {
                Steve.printDivider();
                for (int i = 0; i < userList.size(); i++) {
                    boolean done = true;
                    System.out.println(i + 1 + ". " + "[" + (done ? "X" : "") + "] " + userList.get(i));
                }
                Steve.printDivider();
                continue;
            }

            userList.add(inpt);
            Steve.printDivider();
            System.out.println("added: " + inpt);
            Steve.printDivider();
        }


        Steve.printDivider();
        System.out.println("Bye! Glad I could help!");
    }

    private static void printDivider() {
        System.out.println(Steve.divider);
    }
}
