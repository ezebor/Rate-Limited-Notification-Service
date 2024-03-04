package org.example.notifications;

import org.example.rules.RateLimitRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationTypesManagerImpl implements NotificationTypesManager {

    private RateLimitRule rule;
    private Map<String, List<Notification>> notificationsPerUser;
    private String notificationsType;

    public NotificationTypesManagerImpl(String notificationsType, RateLimitRule rule) {
        this.rule = rule;
        this.notificationsPerUser = new HashMap<>();
        this.notificationsType = notificationsType;
    }

    @Override
    public Boolean shouldSend(Notification notification) {
        notificationsPerUser = this.rule.refresh(notificationsPerUser);
        if(this.rule.shouldSend(notificationsPerUser, notification)) {
            return this.register(notification);
        }
        return false;
    }

    private Boolean register(Notification notification) {
        List<Notification> notifications = notificationsPerUser.getOrDefault(notification.getUserId(), new ArrayList<>());
        notifications.add(notification);
        notificationsPerUser.put(notification.getUserId(), notifications);
        return true;
    }

    public String getNotificationsType() {
        return this.notificationsType;
    }
}
