package org.example.impl;

import org.example.interfaces.Notification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class NotificationImpl implements Notification {

    private String userId;
    private String message;
    private LocalDateTime createdAt;

    public NotificationImpl(String userId, String message) {
        this.userId = userId;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }
}
