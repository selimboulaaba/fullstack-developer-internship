package blueblood.test.backend.DTOs;

public class TaskRequest {
    private String title;
    private String description;
    private boolean completed;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
}
