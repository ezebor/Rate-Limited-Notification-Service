package org.example.rules;

import org.example.notifications.Notification;

import java.time.LocalDateTime;

public class NPerDay extends GenericRule {

    public NPerDay(Integer n) {
        this.n = n;
    }

    @Override
    public Boolean meetsCondition(Notification notification) {
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        return oneDayAgo.isBefore(notification.getCreatedAt());
    }
}
