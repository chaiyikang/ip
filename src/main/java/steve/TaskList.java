package steve;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public void remove(int i) {
        this.taskList.remove(i);
    }

    public int size() {
        return this.taskList.size();
    }

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
