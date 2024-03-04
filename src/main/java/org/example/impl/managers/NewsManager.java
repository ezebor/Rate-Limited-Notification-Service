package org.example.impl.managers;

import org.example.interfaces.Notification;

public class NewsManager extends NotificationTypesManagerImpl{

    @Override
    public Boolean shouldSend(Notification notification) {
        return true;
    }
}
