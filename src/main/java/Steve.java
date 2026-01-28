import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Chatbot called Steve.
 *
 */
public class Steve {
    private static String divider = "    ____________________________________________________________";
    private static ArrayList<Task> userList = new ArrayList<>();

    /**
     * Every user input must start with one of these commands.
     *
    */
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static void main(String[] args) {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");
        Scanner sc = new Scanner(System.in);
        
        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String inpt = sc.nextLine();
                Steve.printDivider();
                String[] inputParts = Steve.parseInput(inpt);
                Command cmd = Command.valueOf(inputParts[0].toUpperCase()); // throws illegalargexc if invalid

                switch (cmd) {
                case BYE:
                    shouldExit = true;
                    System.out.println("Bye! Glad I could help!");
                    break;
                case LIST:
                    Steve.listTasks();
                    break;
                case DELETE:
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
                    break;
                case MARK:
                case UNMARK:
                    if (inputParts.length < 2) {
                        throw new UserException("Please specify the task number.");
                    }
                    int index2 = Integer.parseInt(inputParts[1]) - 1;
                    if (index2 < 0 || index2 > Steve.userList.size() - 1) {

                        throw new UserException("Please specify a valid task number.");
                    }
                    if (inputParts[0].equals("mark")) {
                        Steve.userList.get(index2).setDone();
                        System.out.println("Ok, I've marked it!");
                    } else {
                        Steve.userList.get(index2).setNotDone();
                        System.out.println("Okay, I've unmarked it!");
                    }
                    Steve.listTasks();
                    break;
                case TODO:
                    if (inputParts.length < 2) {
                        throw new UserException("The description of a todo cannot be empty.");
                    }
                    String desc = inpt.substring(5).trim();
                    Task newTask = new Todo(desc);
                    Steve.userList.add(newTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newTask);
                    Steve.reportListSize();
                    break;
                case DEADLINE:
                    if (inputParts.length < 2) {
                        throw new UserException("The description of a deadline cannot be empty.");
                    }
                    String[] parts = inpt.substring(9).split(" /by ");
                    if (parts.length < 2) {
                        throw new UserException("Please specify a deadline using /by.");
                    }
                    Task newDeadlineTask = new Deadline(parts[0], parts[1]);
                    Steve.userList.add(newDeadlineTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newDeadlineTask);
                    Steve.reportListSize();
                    break;
                case EVENT:
                    if (inputParts.length < 2) {
                        throw new UserException("The description of an event cannot be empty.");
                    }
                    String[] eventParts = inpt.substring(6).split(" /from ");
                    if (eventParts.length < 2) {
                        throw new UserException("Please specify the start time using /from.");
                    }
                    String eventDesc = eventParts[0];
                    String[] timeParts = eventParts[1].split(" /to ");
                    if (timeParts.length < 2) {
                        throw new UserException("Please specify the end time using /to.");
                    }
                    Task newEventTask = new Event(eventDesc, timeParts[0], timeParts[1]);
                    Steve.userList.add(newEventTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newEventTask);
                    Steve.reportListSize();
                    break;
                }
            } catch (UserException e) {
                System.out.println("Bro, don't you know how to use me?");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Bruh I don't know what that means :-(");
            } catch (Exception e) {
                System.out.println("Uh oh, the guy who made me didn't realise this was gonna happen...");
                System.out.println(e.getMessage());
            } finally {
                Steve.printDivider();
            }
        }
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
