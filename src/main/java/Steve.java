import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * A Chatbot called Steve.
 *
 */
public class Steve {
    private static String divider = "    __________________________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Every user input must start with one of these commands.
     *
    */
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static void main(String[] args) {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");

        String filePath = "../data/duke.txt";
        File f = new File(filePath);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        if (!f.exists()) {
            try {
                f.createNewFile();       
            } catch (IOException e) {
                System.out.println("Uh oh, some I/O error occured...Please restart the program");
                return;
            }
        }
        try {
            Steve.loadTasks();    
        } catch (IOException e) {
            System.out.println("Uh oh, some I/O error occured...Please restart the program");
            return;
        }



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
                    if (index < 0 || index > Steve.taskList.size() - 1) {

                        throw new UserException("Please specify a valid task number.");
                    }
                    Task taskToDelete = taskList.get(index);
                    Steve.taskList.remove(index);
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
                    if (index2 < 0 || index2 > Steve.taskList.size() - 1) {

                        throw new UserException("Please specify a valid task number.");
                    }
                    if (inputParts[0].equals("mark")) {
                        Steve.taskList.get(index2).setDone();
                        System.out.println("Ok, I've marked it!");
                    } else {
                        Steve.taskList.get(index2).setNotDone();
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
                    Steve.addTaskAndSave(newTask);
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
                    Steve.addTaskAndSave(newDeadlineTask);
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
                    Steve.addTaskAndSave(newEventTask);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newEventTask);
                    Steve.reportListSize();
                    break;
                }
            } catch (UserException e) {
                System.out.println("Bro, don't you know how to use me?");
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format bruh. Please use the format yyyy-mm-dd.");
            }
              catch (IllegalArgumentException e) {
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
                Steve.printDivider();
            }
        }
    }

    private static void reportListSize() {
        System.out.println("     Now you have " + Steve.taskList.size() + " tasks in the list.");
    }

    private static void listTasks() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < Steve.taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + Steve.taskList.get(i).toString());
        }
    }

    private static void printDivider() {
        System.out.println(Steve.divider);
    }

    private static String[] parseInput(String input) {
        return input.split(" ");
    }

    private static void save() throws IOException {
        FileWriter fw = new FileWriter("../data/duke.txt");
        for (Task t : Steve.taskList) {
            fw.write(t.toFileString() + System.lineSeparator());
        }
        fw.close();
        
    }

    private static void addTaskAndSave(Task t) throws IOException{
        Steve.taskList.add(t);
        Steve.save();
    }

    private static void loadTasks() throws IOException {
        File f = new File("../data/duke.txt");

        try {
            Scanner s = new Scanner(f);
    
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\$ ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task t = null;
                switch (type) {
                case "T":
                    t = new Todo(description);
                    break;
                case "D":
                    String by = parts[3]; 
                    t = new Deadline(description, by);
                    break;
                case "E":
                    String start = parts[3];
                    String end = parts[4]; 
                    t = new Event(description, start, end); 
                    break;
                default:
                    System.out.println("Unknown task type in file: " + type);
                }

                if (t == null) {
                    continue;
                }
                if (isDone) {
                    t.setDone();
                }
                Steve.taskList.add(t);
                
            }
            s.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error loading file. Data may be corrupted.");
            System.out.println(e.getMessage());
        }

    }
}
