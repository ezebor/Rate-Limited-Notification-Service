package org.example.notifications;

import junit.framework.TestCase;
import org.example.gateways.Gateway;
import org.example.gateways.GatewayImpl;
import org.example.rules.NPerDay;
import org.example.rules.NPerHour;
import org.example.rules.NPerMinute;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.notifications.NotificationService.NEWS;
import static org.mockito.Mockito.*;

public class AppTest extends TestCase {
    public void testNPerMinuteOnlyAllowsNNotificationsInTheLastMinutePerUser()
    {
        Gateway gateway = mock(GatewayImpl.class);
        String userId = "user 1";
        String message = "message 1";
        NotificationTypesManager manager = new NotificationTypesManagerImpl(NEWS, new NPerMinute(3));
        NotificationService notificationService = new NotificationServiceImpl(gateway, List.of(manager));

        for(int i = 1; i <= 4; i++){
            notificationService.send(NEWS, userId, message);
        }
        notificationService.send(NEWS, "user 2", message);

        verify(gateway, times(3)).send(userId, message);
        verify(gateway, times(1)).send("user 2", message);
    }

    public void testNPerHourOnlyAllowsNNotificationsInTheLastHourPerUser()
    {
        Gateway gateway = mock(GatewayImpl.class);
        String userId = "user 1";
        String message = "message 1";
        NotificationTypesManager manager = new NotificationTypesManagerImpl(NEWS, new NPerHour(3));
        NotificationService notificationService = new NotificationServiceImpl(gateway, List.of(manager));

        for(int i = 1; i <= 4; i++){
            notificationService.send(NEWS, userId, message);
        }
        notificationService.send(NEWS, "user 2", message);

        verify(gateway, times(3)).send(userId, message);
        verify(gateway, times(1)).send("user 2", message);
    }

    public void testNPerDayOnlyAllowsNNotificationsInTheLastDayPerUser()
    {
        Gateway gateway = mock(GatewayImpl.class);
        String userId = "user 1";
        String message = "message 1";
        NotificationTypesManager manager = new NotificationTypesManagerImpl(NEWS, new NPerDay(3));
        NotificationService notificationService = new NotificationServiceImpl(gateway, List.of(manager));

        for(int i = 1; i <= 4; i++){
            notificationService.send(NEWS, userId, message);
        }
        notificationService.send(NEWS, "user 2", message);

        verify(gateway, times(3)).send(userId, message);
        verify(gateway, times(1)).send("user 2", message);
    }

    public void testWhenTheRateLimitEndsThenNewNotificationsAreAllowed()
    {
        Gateway gateway = mock(GatewayImpl.class);
        String userId = "user 1";
        String message = "message 1";
        NotificationTypesManager manager = new NotificationTypesManagerImpl(NEWS, new NPerMinute(3));
        NotificationService notificationService = new NotificationServiceImpl(gateway, List.of(manager));

        // 3 notifications are sent since 3 is the rate limit
        for(int i = 1; i <= 4; i++){
            notificationService.send(NEWS, userId, message);
        }
        LocalDateTime oneMinuteInTheFuture = LocalDateTime.now().plusMinutes(1);

        try (MockedStatic<LocalDateTime> dateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class)) {
            dateTimeMockedStatic.when(LocalDateTime::now).thenReturn(oneMinuteInTheFuture);

            // After a minute 3 new notifications are sent
            for(int i = 1; i <= 3; i++){
                notificationService.send(NEWS, userId, message);
            }

            verify(gateway, times(6)).send(userId, message);
        }
    }
}
