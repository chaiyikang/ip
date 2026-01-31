package steve;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Returns a new Event object.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event (String description, String start, String end) {
        super(description);
        this.start = parseDateTime(start);
        this.end = parseDateTime(end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " 
            + this.description + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) 
            + " to: " + end.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "E $ " + (this.isDone ? "1" : "0") + " $ " + this.description + " $ " + this.start + " $ " + this.end;
    }
    
}
