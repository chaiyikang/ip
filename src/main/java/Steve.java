import java.util.Scanner;
import java.util.ArrayList;

public class Steve {
    private static String divider = "----------";

    public static void main(String[] args) {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> userList = new ArrayList<>();


        while (true) {
            try {
                String inpt = sc.nextLine();
                String[] inputParts = Steve.parseInput(inpt);
                System.out.println(inputParts);
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
    
                if (inputParts[0].equals("mark") || inputParts[0].equals("unmark")) {
                    if (inputParts.length < 2) {
                        throw new UserException("Please specify the task number.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    if (index < 0 || index > userList.size() - 1) {
                        throw new UserException("Please specify a valid task number.");
                    }
                    if (inputParts[0].equals("mark")) {
                        userList.get(index).setDone();
                    } else {
                        userList.get(index).setNotDone();
                    }
                    continue;
                }
    
                Task newTask = new Task(inpt);
                userList.add(newTask);
                Steve.printDivider();
                System.out.println("added: " + newTask.getDescription());
                Steve.printDivider();
                
            } catch (UserException e) {
                System.out.println("Bro, don't you know how to use me?");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                Steve.printDivider();
                System.out.println("Uh oh, the guy who made me didn't realise this was gonna happen...");
                System.out.println(e.getMessage());
            }
        }


        Steve.printDivider();
        System.out.println("Bye! Glad I could help!");
    }

    private static void printDivider() {
        System.out.println(Steve.divider);
    }

    private static String[] parseInput(String input) {
        return input.split(" ");
    }
}
