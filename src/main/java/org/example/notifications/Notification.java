package org.example.notifications;

import java.time.LocalDateTime;

public class Notification {

    private String userId;
    private String message;
    private LocalDateTime createdAt;

    public Notification(String userId, String message) {
        this.userId = userId;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public String getUserId() {
        return this.userId;
    }
}
