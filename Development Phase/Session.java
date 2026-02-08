import java.time.LocalDateTime;

public class session {
    private String sessionId;
    private int userId;
    private LocalDateTime loginTime;
    private boolean isActive;

    public session(String sessionId, int userId) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.loginTime = LocalDateTime.now();
        this.isActive = true;
    }

    public void terminateSession() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}