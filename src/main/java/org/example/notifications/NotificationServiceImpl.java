package org.example.notifications;

import org.example.gateways.Gateway;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private Gateway gateway;
    private List<NotificationTypesManager> notificationTypesManagers;

    public NotificationServiceImpl(Gateway gateway, List<NotificationTypesManager> notificationTypesManagers) {
        super();
        this.gateway = gateway;
        this.notificationTypesManagers = notificationTypesManagers;
    }

    public void send(String type, String userId, String message) {
        Notification notification = new Notification(userId, message);
        if(notificationTypesManagers.stream().anyMatch(manager -> manager.getNotificationsType().equals(type) && manager.shouldSend(notification))) {
            System.out.println("[NotificationService] Sending message " + message + " of type " + type + " to user " + userId);
            this.gateway.send(userId, message);
        } else {
            System.out.println("[NotificationService] Fail to send message " + message + " of type " + type + " to user " + userId);
        }
    }
}
