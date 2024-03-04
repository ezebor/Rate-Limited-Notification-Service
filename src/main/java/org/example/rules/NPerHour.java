package org.example.rules;

import org.example.notifications.Notification;

import java.time.LocalDateTime;

public class NPerHour extends GenericRule {

    public NPerHour(Integer n) {
        this.n = n;
    }

    @Override
    protected Boolean meetsCondition(Notification notification) {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        return oneHourAgo.isBefore(notification.getCreatedAt());
    }
}
