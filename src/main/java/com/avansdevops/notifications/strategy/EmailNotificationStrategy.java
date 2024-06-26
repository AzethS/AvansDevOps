package com.avansdevops.notifications.strategy;

import com.avansdevops.AvansDevOps;

/**
 * Strategy Pattern (Behavioral)
 */
public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        AvansDevOps.LOGGER.info("Sending Email notification: {}", message);
    }
}
