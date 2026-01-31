package steve;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Returns a new TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Removes a task from the list.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        this.taskList.remove(i);
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.taskList.add(t);
    }
    
}
