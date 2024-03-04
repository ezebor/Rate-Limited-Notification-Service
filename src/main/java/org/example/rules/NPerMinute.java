package org.example.rules;

import org.example.notifications.Notification;

import java.time.LocalDateTime;

public class NPerMinute extends GenericRule {

    public NPerMinute(Integer n) {
        this.n = n;
    }

    @Override
    public Boolean meetsCondition(Notification notification) {
        LocalDateTime oneMinuteAgo = LocalDateTime.now().minusMinutes(1);
        return oneMinuteAgo.isBefore(notification.getCreatedAt());
    }
}
