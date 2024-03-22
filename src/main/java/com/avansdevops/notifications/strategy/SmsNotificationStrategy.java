package com.avansdevops.notifications.strategy;

import com.avansdevops.AvansDevOps;

public class SmsNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        AvansDevOps.LOGGER.info("Sending SMS notification: {}", message);
    }
}
