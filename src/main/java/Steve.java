import java.util.Scanner;
import java.util.ArrayList;

public class Steve {
    private static String divider = "    ____________________________________________________________";
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

                if (inputParts[0].equals("delete")) {
                    if (inputParts.length < 2) {
                        throw new UserException("Please specify the task number.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    if (index < 0 || index > Steve.userList.size() - 1) {

                        throw new UserException("Please specify a valid task number.");
                    }
                    Task taskToDelete = userList.get(index);
                    Steve.userList.remove(index);
                    System.out.println("Poof! The task is deleted: ");
                    System.out.println("    " + taskToDelete.toString());
                    Steve.reportListSize();
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
    
                if (inputParts[0].equals("todo")) {
                    if (inputParts.length < 2) {
                         throw new UserException("The description of a todo cannot be empty.");
                    }
                    String desc = inpt.substring(5).trim();
                    Task newTask = new Todo(desc);
                    Steve.userList.add(newTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newTask);
                    Steve.reportListSize();
                } else if (inputParts[0].equals("deadline")) {
                    if (inputParts.length < 2) {
                         throw new UserException("The description of a deadline cannot be empty.");
                    }
                    String[] parts = inpt.substring(9).split(" /by ");
                    if (parts.length < 2) {
                         throw new UserException("Please specify a deadline using /by.");
                    }
                    Task newTask = new Deadline(parts[0], parts[1]);
                    Steve.userList.add(newTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newTask);
                    Steve.reportListSize();
                } else if (inputParts[0].equals("event")) {
                    if (inputParts.length < 2) {
                         throw new UserException("The description of an event cannot be empty.");
                    }
                    String[] parts = inpt.substring(6).split(" /from ");
                    if (parts.length < 2) {
                         throw new UserException("Please specify the start time using /from.");
                    }
                    String desc = parts[0];
                    String[] timeParts = parts[1].split(" /to ");
                    if (timeParts.length < 2) {
                         throw new UserException("Please specify the end time using /to.");
                    }
                    Task newTask = new Event(desc, timeParts[0], timeParts[1]);
                    Steve.userList.add(newTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newTask);
                    Steve.reportListSize();
                } else {
                    throw new UserException("Bruh I don't know what that means :-(");
                }
                
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

    private static void reportListSize() {
        System.out.println("     Now you have " + Steve.userList.size() + " tasks in the list.");

    }

    private static void listTasks() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < Steve.userList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + Steve.userList.get(i).toString());
        }
    }

    private static void printDivider() {
        System.out.println(Steve.divider);
    }

    private static String[] parseInput(String input) {
        return input.split(" ");
    }
}
