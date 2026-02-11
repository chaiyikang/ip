package steve;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A Chatbot called Steve.
 *
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
     * Gets response from the chatbot.
     * @param input
     * @return a String which is the response of the chatbot
     */
    public String getResponse(String input) {
        try {
            String[] inputParts = this.parser.getInputParts(input);
            Command cmd = this.parser.getCommand(inputParts);

            switch (cmd) {
            case BYE:
                return this.ui.sayBye();
            case LIST:
                return this.storage.listTasks();
            case DELETE:
                if (inputParts.length < 2) {
                    throw new UserException("Please specify the task number.");
                }
                int index = Integer.parseInt(inputParts[1]) - 1;
                if (index < 0 || index > this.storage.getTaskListSize() - 1) {

                    throw new UserException("Please specify a valid task number.");
                }
                assert index >= 0 && index < this.storage.getTaskListSize();
                Task taskToDelete = this.storage.getTask(index);
                this.storage.removeFromTaskList(index);
                this.storage.save();
                return "Poof! The task is deleted: " + "\n"
                        + "    " + taskToDelete.toString() + "\n"
                        + this.storage.reportListSize();
            case MARK:
            case UNMARK:
                if (inputParts.length < 2) {
                    throw new UserException("Please specify the task number.");
                }
                int index2 = Integer.parseInt(inputParts[1]) - 1;
                if (index2 < 0 || index2 > this.storage.getTaskListSize() - 1) {

                    throw new UserException("Please specify a valid task number.");
                }
                assert index2 >= 0 && index2 < this.storage.getTaskListSize();
                if (inputParts[0].equals("mark")) {
                    this.storage.markTask(index2);
                    return "Ok, I've marked it!" + "\n"
                            + this.storage.listTasks();

                } else {
                    this.storage.unmarkTask(index2);
                    return "Okay, I've unmarked it!" + "\n"
                            + this.storage.listTasks();
                }
            case TODO:
                if (inputParts.length < 2) {
                    throw new UserException("The description of a todo cannot be empty.");
                }
                String desc = this.parser.getRawInput().substring(5).trim();
                Task newTask = new Todo(desc);
                this.storage.addTaskAndSave(newTask);
                return "     Got it. I've added this task:" + "\n"
                        + "       " + newTask + "\n"
                        + this.storage.reportListSize();
            case DEADLINE:
                if (inputParts.length < 2) {
                    throw new UserException("The description of a deadline cannot be empty.");
                }
                String[] parts = this.parser.getRawInput().substring(9).split(" /by ");
                if (parts.length < 2) {
                    throw new UserException("Please specify a deadline using /by.");
                }
                Task newDeadlineTask = new Deadline(parts[0], parts[1]);
                this.storage.addTaskAndSave(newDeadlineTask);
                return "     Got it. I've added this task:" + "\n"
                        + "       " + newDeadlineTask + "\n"
                        + this.storage.reportListSize();
            case EVENT:
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
                return "     Got it. I've added this task:" + "\n"
                        + "       " + newEventTask + "\n"
                        + this.storage.reportListSize();
            case FIND:
                if (inputParts.length < 2) {
                    throw new UserException("Search query cannot be empty!!!");
                }
                String searchString = this.parser.getRawInput().substring(6);
                ArrayList<Task> matchingTasks = this.storage.find(searchString);
                return matchingTasks.size() + " results were found: " + "\n"
                        + this.ui.printListOfTasks(matchingTasks);
            default:
                throw new UserException("Invalid command. Commands are: todo, deadline, event, list, mark, unmark, delete, find, bye");
            }
        } catch (UserException e) {
            return "Bro, don't you know how to use me?" + "\n"
                    + e.getMessage();
        } catch (DateTimeParseException e) {
            return "Invalid date format bruh. Please use the format yyyy-mm-dd.";
        } catch (IllegalArgumentException e) {
            String availableCommands = "";
            for (Command c : Command.values()) {
                availableCommands += c.toString() + "\n";
            }
            return "Invalid command. Commands are:" + "\n"
                    + availableCommands;
        } catch (IOException e) {
            return "Uh oh, some I/O error occured...Please restart the program";
        } catch (Exception e) {
            return "Uh oh, the guy who made me didn't realise this was gonna happen..." + "\n"
                    + e.getMessage();
        }
    }
}