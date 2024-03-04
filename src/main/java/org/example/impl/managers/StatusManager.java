package org.example.impl.managers;

import org.example.impl.managers.NotificationTypesManagerImpl;
import org.example.interfaces.Notification;

public class StatusManager extends NotificationTypesManagerImpl {

    @Override
    public Boolean shouldSend(Notification notification) {
        return true;
    }
}
