public class Deadline extends Task {
    private String timeDescription;

    public Deadline (String description, String timeDescription) {
        super(description);
        this.timeDescription = timeDescription;
    }

    @Override
    public String toString() {
        return "[D] [" + (this.isDone ? 'X' : "") + "] " + this.description + "(by: " + this.timeDescription + ")";
    }
    
}
