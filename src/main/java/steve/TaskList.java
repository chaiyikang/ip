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
        assert i >= 0 && i < this.taskList.size();
        return this.taskList.get(i);
    }

    /**
     * Removes a task from the list.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        assert i >= 0 && i < this.taskList.size();
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
        
    /**
     * Finds tasks in the list whose descriptions contain the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of Task objects whose descriptions contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
