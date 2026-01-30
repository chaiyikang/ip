import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event (String description, String start, String end) {
        super(description);
        this.start = parseDateTime(start);
        this.end = parseDateTime(end);
    }

    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " 
            + this.description + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) 
            + " to: " + end.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E $ " + (this.isDone ? "1" : "0") + " $ " + this.description + " $ " + this.start + " $ " + this.end;
    }
    
}
