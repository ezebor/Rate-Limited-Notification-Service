package org.example.notifications;

public interface NotificationService {

    String STATUS = "status";
    String NEWS = "news";
    String MARKETING = "marketing";

    void send(String type, String userId, String message);
}
