package steve;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime by;


    /**
     * Returns a new Deadline object.
     *
     * @param description The description of the deadline.
     * @param timeDescription The deadline of the task.
     */
    public Deadline (String description, String timeDescription) {
        super(description);
        this.by = this.parseDateTime(timeDescription);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " 
                + this.description + " (by: " + by.format(Task.DATE_FORMAT) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "D $ " + (this.isDone ? "1" : "0") + " $ " + this.description + " $ " + this.by;
    }
    
}
