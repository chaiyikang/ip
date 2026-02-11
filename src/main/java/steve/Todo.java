package steve;
/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Returns a new Todo object.
     * @param description The description of the todo.
     */
    public Todo (String description) {
        super(description);
        assert description != null && !description.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "X" : " ") + "] " + this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "T $ " + (this.isDone ? "1" : "0") + " $ " + this.description;
    }


    
}
