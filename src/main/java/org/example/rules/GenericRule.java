package org.example.rules;

import org.example.notifications.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericRule implements RateLimitRule {
    protected Integer n;

    @Override
    public Map<String, List<Notification>> refresh(Map<String, List<Notification>> notificationsPerUser) {
        Map<String, List<Notification>> notificationsRefreshed = new HashMap<>();
        notificationsPerUser.keySet().forEach(userId -> notificationsRefreshed.put(
                userId,
                filterLastOutdatedNotifications(notificationsPerUser, userId)
        ));
        return notificationsRefreshed;
    }

    private List<Notification> filterLastOutdatedNotifications(Map<String, List<Notification>> notificationsPerUser, String userId) {
        List<Notification> notifications = notificationsPerUser.getOrDefault(userId, new ArrayList<>());
        if(!this.meetsCondition(notifications.getLast())){
            notifications.removeLast();
        }
        return notifications;
    }

    @Override
    public Boolean shouldSend(Map<String, List<Notification>> notificationsPerUser, Notification notification) {
        return notificationsPerUser.getOrDefault(notification.getUserId(), new ArrayList<>()).size() < n
                && meetsCondition(notification);
    }

    protected abstract Boolean meetsCondition(Notification notification);
}
