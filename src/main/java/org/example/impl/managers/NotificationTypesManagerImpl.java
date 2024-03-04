package org.example.impl.managers;

import org.example.interfaces.Notification;
import org.example.interfaces.NotificationTypesManager;

public abstract class NotificationTypesManagerImpl implements NotificationTypesManager {

    @Override
    public Boolean shouldSend(Notification notification) {
        return false;
    }
}
