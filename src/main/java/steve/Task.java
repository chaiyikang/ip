package steve;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns a new Task object.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }

    /**
     * Returns the string representation of the task to be saved in the data file.
     */
    public abstract String toFileString();

    /**
     * Parses a string into a LocalDateTime object.
     * Handles both ISO-8601 format and yyyy-MM-dd format.
     *
     * @param timeString The string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException If the string cannot be parsed.
     */
    protected LocalDateTime parseDateTime(String timeString) throws DateTimeParseException {
        try {
            // handles ISO-8601 format for data file
            return LocalDateTime.parse(timeString);
        } catch (DateTimeParseException e) {
            // handles yyyy-MM-dd format
            return java.time.LocalDate.parse(timeString, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
            
        }
    }
    
}
