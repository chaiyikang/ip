package steve;

import java.util.ArrayList;

public class Ui {
    private static final String DIVIDER = "    __________________________________________________________";

    public void greet() {
        System.out.println("Hello, I'm Steve!\nWhat can I help you with?");
    }

    public void sayBye() {
        System.out.println("Bye! Glad I could help!");

    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printListOfTasks(ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + listOfTasks.get(i).toString());
        }
    }
    
}
