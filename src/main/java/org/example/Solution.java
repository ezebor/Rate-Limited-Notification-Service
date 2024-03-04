package org.example;

import org.example.gateways.GatewayImpl;
import org.example.notifications.NotificationService;
import org.example.notifications.NotificationServiceImpl;
import org.example.notifications.NotificationTypesManager;
import org.example.notifications.NotificationTypesManagerImpl;
import org.example.rules.NPerDay;
import org.example.rules.NPerHour;
import org.example.rules.NPerMinute;

import java.util.ArrayList;
import java.util.List;

import static org.example.notifications.NotificationService.*;

class Solution {
    public static void main(String[] args) {

        List<NotificationTypesManager> notificationTypesManagers = new ArrayList<>();
        notificationTypesManagers.add(new NotificationTypesManagerImpl(STATUS, new NPerMinute(2)));
        notificationTypesManagers.add(new NotificationTypesManagerImpl(NEWS, new NPerDay(1)));
        notificationTypesManagers.add(new NotificationTypesManagerImpl(MARKETING, new NPerHour(3)));

        NotificationService service = new NotificationServiceImpl(new GatewayImpl(), notificationTypesManagers);

        service.send(NEWS, "user", "news 1");
        service.send(NEWS, "user", "news 2");
        service.send(NEWS, "user", "news 3");
        service.send(NEWS, "another user", "news 1");
        service.send(STATUS, "user", "update 1");
        service.send(MARKETING, "user", "marketing 1");
        service.send(MARKETING, "user", "marketing 2");
        service.send(MARKETING, "user", "marketing 3");
        service.send(MARKETING, "user", "marketing 4");
    }

}
