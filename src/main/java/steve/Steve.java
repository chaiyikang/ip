package steve;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A Chatbot called Steve.
 */
public class Steve {
    private Ui ui;
    private Storage storage;
    private Parser parser;

    /**
     * Creates an instance of the Steve Chatbot.
     */
    public Steve() {
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage = new Storage();
            this.storage.loadTasks();
        } catch (IOException e) {
            System.out.println("Uh oh, some I/O error occured...Please restart the program");
        }
    }

    /**
     * Helper method to parse the index from input and validate it against the task list.
     * Improvements: DRY principle (reused by delete, mark, tag).
     */
    private int parseAndValidateIndex(String[] inputParts) throws UserException {
        if (inputParts.length < 2) {
            throw new UserException("Please specify the task number.");
        }

        int index;
        try {
            index = Integer.parseInt(inputParts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new UserException("That is not a valid number.");
        }

        if (index < 0 || index >= this.storage.getTaskListSize()) {
            throw new UserException("Please specify a valid task number.");
        }

        return index;
    }

    private String handleDelete(String[] inputParts) throws UserException, IOException {
        // Improvement: logic delegates to helper method
        int index = parseAndValidateIndex(inputParts);
        
        Task taskToDelete = this.storage.getTask(index);
        this.storage.removeFromTaskList(index);
        this.storage.save();
        
        return "Poof! The task is deleted: \n"
                + "    " + taskToDelete.toString() + "\n"
                + this.storage.reportListSize();
    }

    private String handleMark(String[] inputParts) throws UserException, IOException {
        // Improvement: logic delegates to helper method
        int index = parseAndValidateIndex(inputParts);

        if (inputParts[0].equals("mark")) {
            this.storage.markTask(index);
            this.storage.save();
            return "Ok, I've marked it!\n" + this.storage.listTasks();
        } else {
            this.storage.unmarkTask(index);
            this.storage.save();
            return "Okay, I've unmarked it!\n" + this.storage.listTasks();
        }
    }

    private String handleToDo(String[] inputParts) throws UserException, IOException {
        if (inputParts.length < 2) {
            throw new UserException("The description of a todo cannot be empty.");
        }
        String desc = this.parser.getRawInput().substring(5).trim();
        Task newTask = new Todo(desc);
        this.storage.addTaskAndSave(newTask);
        return "    Got it. I've added this task:\n"
                + "       " + newTask + "\n"
                + this.storage.reportListSize();
    }

    private String handleDeadline(String[] inputParts) throws UserException, IOException {
        if (inputParts.length < 2) {
            throw new UserException("The description of a deadline cannot be empty.");
        }
        String[] parts = this.parser.getRawInput().substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new UserException("Please specify a deadline using /by.");
        }
        Task newDeadlineTask = new Deadline(parts[0], parts[1]);
        this.storage.addTaskAndSave(newDeadlineTask);
        return "    Got it. I've added this task:\n"
                + "       " + newDeadlineTask + "\n"
                + this.storage.reportListSize();
    }

    private String handleEvent(String[] inputParts) throws UserException, IOException {
        if (inputParts.length < 2) {
            throw new UserException("The description of an event cannot be empty.");
        }
        String[] eventParts = this.parser.getRawInput().substring(6).split(" /from ");
        if (eventParts.length < 2) {
            throw new UserException("Please specify the start time using /from.");
        }
        String eventDesc = eventParts[0];
        String[] timeParts = eventParts[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new UserException("Please specify the end time using /to.");
        }
        Task newEventTask = new Event(eventDesc, timeParts[0], timeParts[1]);
        this.storage.addTaskAndSave(newEventTask);
        return "    Got it. I've added this task:\n"
                + "       " + newEventTask + "\n"
                + this.storage.reportListSize();
    }

    private String handleFind(String[] inputParts) throws UserException, IOException {
        if (inputParts.length < 2) {
            throw new UserException("Search query cannot be empty!!!");
        }
        String searchString = this.parser.getRawInput().substring(6);
        ArrayList<Task> matchingTasks = this.storage.find(searchString);
        return matchingTasks.size() + " results were found: \n"
                + this.ui.printListOfTasks(matchingTasks);
    }

    private String handleTag(String[] inputParts) throws UserException, IOException {
        if (inputParts.length < 3) {
            throw new UserException("Please specify the task number and the tag e.g. tag 1 #fun");
        }
        
        // Improvement: logic delegates to helper method
        int index = parseAndValidateIndex(inputParts);
        
        String tag = inputParts[2];
        if (tag.startsWith("#")) {
            tag = tag.substring(1);
        }
        this.storage.getTask(index).setTag(tag);
        this.storage.save();
        return "Ok, I've tagged this task:\n"
                + "    " + this.storage.getTask(index).toString();
    }

    private String handleCommand(Command cmd, String[] inputParts) throws UserException, IOException {
        switch (cmd) {
        case BYE:
            return this.ui.sayBye();
        case LIST:
            return this.storage.listTasks();
        case DELETE:
            return this.handleDelete(inputParts);
        case MARK:
        case UNMARK:
            return this.handleMark(inputParts);
        case TODO:
            return this.handleToDo(inputParts);
        case DEADLINE:
            return this.handleDeadline(inputParts);
        case EVENT:
            return this.handleEvent(inputParts);
        case FIND:
            return this.handleFind(inputParts);
        case TAG:
            return this.handleTag(inputParts);
        default:
            throw new UserException(
                    "Invalid command. Commands are: todo, deadline, event, list, mark, unmark, delete, find, bye");
        }
    }

    /**
     * Gets response from the chatbot.
     * @param input
     * @return a String which is the response of the chatbot
     */
    public String getResponse(String input) {
        try {
            String[] inputParts = this.parser.getInputParts(input);
            Command cmd = this.parser.getCommand(inputParts);
            return handleCommand(cmd, inputParts);
        } catch (UserException e) {
            return "Bro, don't you know how to use me?\n" + e.getMessage();
        } catch (DateTimeParseException e) {
            return "Invalid date format bruh. Please use the format yyyy-mm-dd.";
        } catch (NumberFormatException e) {
            // Improvement: Specific handling for non-integer inputs in commands
            return "Please enter a valid numeric task ID.";
        } catch (IllegalArgumentException e) {
            // Improvement: StringBuilder for loop concatenation
            StringBuilder availableCommands = new StringBuilder();
            for (Command c : Command.values()) {
                availableCommands.append(c.toString()).append("\n");
            }
            return "Invalid command. Commands are:\n" + availableCommands.toString();
        } catch (IOException e) {
            return "Uh oh, some I/O error occured...Please restart the program";
        } catch (Exception e) {
            return "Uh oh, the guy who made me didn't realise this was gonna happen...\n"
                    + e.getMessage();
        }
    }
}