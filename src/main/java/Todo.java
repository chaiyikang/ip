public class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "X" : " ") + "] " + this.description;
    }

    @Override
    public String toFileString() {
        return "T $ " + (this.isDone ? "1" : "0") + " $ " + this.description;
    }


    
}
