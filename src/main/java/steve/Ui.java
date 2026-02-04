package steve;

import java.util.ArrayList;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    /**
     * Prints a greeting to the user.
     */
    public String greet() {
        return "Hello, I'm Steve!\nWhat can I help you with?";
    }

    /**
     * Prints a farewell message to the user.
     */
    public String sayBye() {
        return "Bye! Glad I could help!";
    }

    /**
     * Returns a string of the list of tasks
     * @param listOfTasks
     * @return a String of the list of tasks
     */
    public String printListOfTasks(ArrayList<Task> listOfTasks) {
        String result = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            result += "     " + (i + 1) + "." + listOfTasks.get(i).toString() + "\n";
        }
        return result;
    }
}