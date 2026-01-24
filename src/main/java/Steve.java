import java.util.Scanner;
import java.util.ArrayList;

public class Steve {
    private static String divider = "----------";
    private static ArrayList<Task> userList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");

        Scanner sc = new Scanner(System.in);
        
        while (true) {
            try {
                String inpt = sc.nextLine();
                Steve.printDivider();
                String[] inputParts = Steve.parseInput(inpt);
                if (inpt.equals("bye")) {
                    break;
                }
                
                if (inpt.equals(("list"))) {
                    Steve.listTasks();
                    continue;
                }
    
                if (inputParts[0].equals("mark") || inputParts[0].equals("unmark")) {
                    if (inputParts.length < 2) {
                        throw new UserException("Please specify the task number.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    if (index < 0 || index > Steve.userList.size() - 1) {

                        throw new UserException("Please specify a valid task number.");
                    }
                    if (inputParts[0].equals("mark")) {
                        Steve.userList.get(index).setDone();
                        System.out.println("Ok, I've marked it!");
                    } else {
                        Steve.userList.get(index).setNotDone();
                        System.out.println("Okay, I've unmarked it!");
                    }
                    Steve.listTasks();

                    continue;
                }
    
                Task newTask = new Task(inpt);
                Steve.userList.add(newTask);
                System.out.println("added: " + newTask.getDescription());
                
            } catch (UserException e) {
                System.out.println("Bro, don't you know how to use me?");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Uh oh, the guy who made me didn't realise this was gonna happen...");
                System.out.println(e.getMessage());
            } finally {
                Steve.printDivider();
            }
        }


        System.out.println("Bye! Glad I could help!");
    }

    private static void listTasks() {
        for (int i = 0; i < Steve.userList.size(); i++) {
        System.out.println(i + 1 + ". " + Steve.userList.get(i).toString());
            }

    }

    private static void printDivider() {
        System.out.println(Steve.divider);
    }

    private static String[] parseInput(String input) {
        return input.split(" ");
    }
}
