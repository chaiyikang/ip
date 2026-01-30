import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A Chatbot called Steve.
 *
 */
public class Steve {
    /**
     * Every user input must start with one of these commands.
     *
    */
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greet();

        Storage storage = null;
        try {
            storage = new Storage();
            storage.loadTasks();
        } catch (IOException e) {
            System.out.println("Uh oh, some I/O error occured...Please restart the program");
            return;
        }


        Scanner sc = new Scanner(System.in);
        
        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String inpt = sc.nextLine();
                ui.printDivider();
                String[] inputParts = Steve.parseInput(inpt);
                Command cmd = Command.valueOf(inputParts[0].toUpperCase()); // throws illegalargexc if invalid

                switch (cmd) {
                case BYE:
                    shouldExit = true;
                    ui.sayBye();
                    break;
                case LIST:
                    storage.listTasks();
                    break;
                case DELETE:
                    if (inputParts.length < 2) {
                        throw new UserException("Please specify the task number.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    if (index < 0 || index > storage.getTaskListSize() - 1) {

                        throw new UserException("Please specify a valid task number.");
                    }
                    Task taskToDelete = storage.getTask(index);
                    storage.removeFromTaskList(index);
                    storage.save();
                    System.out.println("Poof! The task is deleted: ");
                    System.out.println("    " + taskToDelete.toString());
                    storage.reportListSize();
                    break;
                case MARK:
                case UNMARK:
                    if (inputParts.length < 2) {
                        throw new UserException("Please specify the task number.");
                    }
                    int index2 = Integer.parseInt(inputParts[1]) - 1;
                    if (index2 < 0 || index2 > storage.getTaskListSize() - 1) {

                        throw new UserException("Please specify a valid task number.");
                    }
                    if (inputParts[0].equals("mark")) {
                        storage.markTask(index2);
                        System.out.println("Ok, I've marked it!");
                    } else {
                        storage.unmarkTask(index2);
                        System.out.println("Okay, I've unmarked it!");
                    }
                    storage.listTasks();
                    break;
                case TODO:
                    if (inputParts.length < 2) {
                        throw new UserException("The description of a todo cannot be empty.");
                    }
                    String desc = inpt.substring(5).trim();
                    Task newTask = new Todo(desc);
                    storage.addTaskAndSave(newTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newTask);
                    storage.reportListSize();
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
                    storage.addTaskAndSave(newDeadlineTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newDeadlineTask);
                    storage.reportListSize();
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
                    storage.addTaskAndSave(newEventTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newEventTask);
                    storage.reportListSize();
                    break;
                }
            } catch (UserException e) {
                System.out.println("Bro, don't you know how to use me?");
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format bruh. Please use the format yyyy-mm-dd.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command. Commands are:");
                for (Command c : Command.values()) {
                    System.out.println(c);
                }
            } catch (IOException e) {
                System.out.println("Uh oh, some I/O error occured...Please restart the program");
                return;
            } catch (Exception e) {
                System.out.println("Uh oh, the guy who made me didn't realise this was gonna happen...");
                System.out.println(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }



    private static String[] parseInput(String input) {
        return input.split(" ");
    }

    
}
