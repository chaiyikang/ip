package steve;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    private static final String FILE_PATH = "./data/steve.txt";

    private TaskList taskList;

    /**
     * Returns a new Storage object.
     * Creates a new file if the data file does not exist.
     *
     * @throws IOException If an I/O error occurs.
     */
    public Storage() throws IOException {
        this.taskList = new TaskList();
        File f = new File(FILE_PATH);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        if (!f.exists()) {
            f.createNewFile();       
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        this.taskList.get(index).setDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmarkTask(int index) {
        this.taskList.get(index).setNotDone();
    }

    public int getTaskListSize() {
        return this.taskList.size();
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task to be removed.
     */
    public void removeFromTaskList(int index) {
        this.taskList.remove(index);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public ArrayList<Task> find(String search) {
        return this.taskList.find(search);
    }

    /**
     * Prints the number of tasks in the list.
     */
    public void reportListSize() {
        System.out.println("     Now you have " + this.taskList.size() + " tasks in the list.");
    }

    /**
     * Prints all the tasks in the list.
     */
    public void listTasks() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + this.taskList.get(i).toString());
        }
    }

    /**
     * Saves the current list of tasks to the data file.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void save() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < this.taskList.size(); i++) {
            Task t = this.taskList.get(i);
            fw.write(t.toFileString() + System.lineSeparator());
        }
        fw.close();
        
    }

    /**
     * Adds a task to the task list and saves the list to the data file.
     *
     * @param t The task to be added.
     * @throws IOException If an I/O error occurs.
     */
    public void addTaskAndSave(Task t) throws IOException{
        this.taskList.add(t);
        this.save();
    }

    /**
     * Loads tasks from the data file into the task list.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void loadTasks() throws IOException {
        File f = new File(FILE_PATH);

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
                this.taskList.add(t);
                
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
