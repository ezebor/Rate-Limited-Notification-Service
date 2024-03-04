package org.example.rules;

import org.example.notifications.Notification;

import java.time.LocalDateTime;

public class OnePerDay extends GenericRule {

    public OnePerDay() {
        this.n = 1;
    }

    @Override
    public Boolean meetsCondition(Notification notification) {
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        return oneDayAgo.isBefore(notification.getCreatedAt());
    }
}
