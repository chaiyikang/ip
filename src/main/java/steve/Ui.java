package steve;

import java.util.ArrayList;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private static final String DIVIDER = "    __________________________________________________________";

    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");
    }

    /**
     * Prints a farewell message to the user.
     */
    public void sayBye() {
        System.out.println("Bye! Glad I could help!");

    }

    /**
     * Prints a divider line.
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printListOfTasks(ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + listOfTasks.get(i).toString());
        }
    }
    
}
