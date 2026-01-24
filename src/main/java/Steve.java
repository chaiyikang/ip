import java.util.Scanner;
import java.util.ArrayList;

public class Steve {
    private static String divider = "----------";

    public static void main(String[] args) {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> userList = new ArrayList<>();


        while (true) {
            String inpt = sc.nextLine();
            if (inpt.equals("bye")) {
                break;
            }
            
            if (inpt.equals(("list"))) {
                Steve.printDivider();
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println(i + 1 + ". " + userList.get(i).toString());
                }
                Steve.printDivider();
                continue;
            }



            Task newTask = new Task(inpt);
            userList.add(newTask);
            Steve.printDivider();
            System.out.println("added: " + newTask.getDescription());
            Steve.printDivider();
        }


        Steve.printDivider();
        System.out.println("Bye! Glad I could help!");
    }

    private static void printDivider() {
        System.out.println(Steve.divider);
    }

    private static String[] parseInput(String input) {
        return input.split(" ")
    }
}
