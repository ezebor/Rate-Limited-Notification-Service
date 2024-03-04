package org.example.impl;

import org.example.impl.managers.NullManager;
import org.example.interfaces.Gateway;
import org.example.interfaces.Notification;
import org.example.interfaces.NotificationService;
import org.example.interfaces.NotificationTypesManager;

import java.util.Map;

public class NotificationServiceImpl implements NotificationService {

    private Gateway gateway;
    private Map<String, NotificationTypesManager> notificationTypesManagers;

    public NotificationServiceImpl(Gateway gateway, Map<String, NotificationTypesManager> notificationTypesManagers) {
        super();
        this.gateway = gateway;
        this.notificationTypesManagers = notificationTypesManagers;
    }

    public void send(String type, String userId, String message) {
        Notification notification = new NotificationImpl(userId, message);
        if(notificationTypesManagers.getOrDefault(type, new NullManager()).shouldSend(notification)) {
            System.out.println("[NotificationService] Sending message " + message + " of type " + type + " to user " + userId);
            this.gateway.send(userId, message);
        } else {
            System.out.println("[NotificationService] Fail to send message " + message + " of type " + type + " to user " + userId);
        }
    }
}
