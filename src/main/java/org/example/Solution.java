package org.example;

import org.example.gateways.GatewayImpl;
import org.example.notifications.NotificationService;
import org.example.notifications.NotificationServiceImpl;
import org.example.notifications.NotificationTypesManager;
import org.example.notifications.NotificationTypesManagerImpl;
import org.example.rules.NPerHour;
import org.example.rules.NPerMinute;
import org.example.rules.OnePerDay;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {

        List<NotificationTypesManager> notificationTypesManagers = new ArrayList<>();
        notificationTypesManagers.add(new NotificationTypesManagerImpl(NotificationService.STATUS, new NPerMinute(2)));
        notificationTypesManagers.add(new NotificationTypesManagerImpl(NotificationService.NEWS, new OnePerDay()));
        notificationTypesManagers.add(new NotificationTypesManagerImpl(NotificationService.MARKETING, new NPerHour(3)));

        NotificationService service = new NotificationServiceImpl(new GatewayImpl(), notificationTypesManagers);

        service.send(NotificationService.NEWS, "user", "news 1");
        service.send(NotificationService.NEWS, "user", "news 2");
        service.send(NotificationService.NEWS, "user", "news 3");
        service.send(NotificationService.NEWS, "another user", "news 1");
        service.send(NotificationService.STATUS, "user", "update 1");
        service.send(NotificationService.MARKETING, "user", "marketing 1");
        service.send(NotificationService.MARKETING, "user", "marketing 2");
        service.send(NotificationService.MARKETING, "user", "marketing 3");
        service.send(NotificationService.MARKETING, "user", "marketing 4");
    }

}
