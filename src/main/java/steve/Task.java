package steve;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

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

    public void setDone() {
        this.isDone = true;
    }

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

    public abstract String toFileString();

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
