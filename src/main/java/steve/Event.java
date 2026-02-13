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
        assert !this.start.isAfter(this.end) : "Start time of event cannot be after end time.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String tagString = "";
        if (!this.tag.isEmpty()) {
            tagString = " #" + this.tag;
        }
        return "[E][" + (this.isDone ? "X" : " ") + "] "
                + this.description + " (from: " + start.format(Task.DATE_FORMAT)
                + " to: " + end.format(Task.DATE_FORMAT) + ")" + tagString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        if (!this.tag.isEmpty()) {
            return "E $ " + (this.isDone ? "1" : "0") + " $ " + this.description + " $ " + this.start
                    + " $ " + this.end + " $ " + this.tag;
        }
        return "E $ " + (this.isDone ? "1" : "0") + " $ " + this.description + " $ " + this.start
                + " $ " + this.end;
    }
    
}
