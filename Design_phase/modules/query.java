import java.time.LocalDateTime;

public class query {
    private int queryId;
    private int userId;
    private String queryText;
    private String category;
    private String priority;
    private String status;
    private boolean isEscalated;
    private LocalDateTime timestamp;

    public query(int queryId, int userId, String queryText, String category) {
        this.queryId = queryId;
        this.userId = userId;
        this.queryText = queryText;
        this.category = category;
        this.status = "Submitted";
        this.isEscalated = false;
        this.timestamp = LocalDateTime.now();
        calculatePriority();
    }

    public boolean validateQuery() {
        return queryText != null && !queryText.isEmpty();
    }

    public void calculatePriority() {
        if (category.equalsIgnoreCase("Technical")) {
            priority = "High";
        } else {
            priority = "Normal";
        }
    }

    public void escalate() {
        isEscalated = true;
        status = "Escalated";
    }

    public String getStatus() {
        return status;
    }
}