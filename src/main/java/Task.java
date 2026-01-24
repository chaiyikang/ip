public class Task {
    private String description;
    private boolean isDone;

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
        return "[" + (this.isDone ? 'X' : "") + "] " + this.description;
    }
    
    
}
