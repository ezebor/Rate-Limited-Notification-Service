package org.example.interfaces;

public interface NotificationService {

    final String STATUS = "status";
    final String NEWS = "news";
    final String MARKETING = "marketing";

    void send(String type, String userId, String message);
}
