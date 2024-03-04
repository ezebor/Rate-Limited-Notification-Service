package org.example.notifications;

public interface NotificationTypesManager {

    String getNotificationsType();
    Boolean shouldSend(Notification notification);
}
