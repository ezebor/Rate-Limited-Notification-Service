package org.example;

import org.example.impl.*;
import org.example.impl.managers.MarketingManager;
import org.example.impl.managers.NewsManager;
import org.example.impl.managers.StatusManager;
import org.example.interfaces.NotificationService;
import org.example.interfaces.NotificationTypesManager;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        Map<String, NotificationTypesManager> notificationTypesManages = new HashMap<>();
        notificationTypesManages.put(NotificationService.NEWS, new NewsManager());
        notificationTypesManages.put(NotificationService.STATUS, new StatusManager());
        notificationTypesManages.put(NotificationService.MARKETING, new MarketingManager());

        NotificationService service = new NotificationServiceImpl(new GatewayImpl(), notificationTypesManages);

        service.send(NotificationService.NEWS, "user", "news 1");
        service.send(NotificationService.NEWS, "user", "news 2");
        service.send(NotificationService.NEWS, "user", "news 3");
        service.send(NotificationService.NEWS, "another user", "news 1");
        service.send(NotificationService.STATUS, "user", "update 1");
        service.send(NotificationService.MARKETING, "user", "marketing 1");

    }

}
