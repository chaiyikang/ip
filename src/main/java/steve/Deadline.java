package steve;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline (String description, String timeDescription) {
        super(description);
        this.by = this.parseDateTime(timeDescription);
    }
    
    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " 
                + this.description + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D $ " + (this.isDone ? "1" : "0") + " $ " + this.description + " $ " + this.by;
    }
    
}
