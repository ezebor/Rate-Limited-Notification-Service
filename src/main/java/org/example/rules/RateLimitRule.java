package org.example.rules;

import org.example.notifications.Notification;

import java.util.List;
import java.util.Map;

public interface RateLimitRule {

    Map<String, List<Notification>> refresh(Map<String, List<Notification>> notificationsPerUser);
    Boolean shouldSend(Map<String, List<Notification>> notificationsPerUser, Notification notification);
}
