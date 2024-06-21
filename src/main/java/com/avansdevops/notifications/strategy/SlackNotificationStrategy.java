package com.avansdevops.notifications.strategy;

import com.avansdevops.AvansDevOps;

/**
 * Strategy Pattern (Behavioral)
 */
public class SlackNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        AvansDevOps.LOGGER.info("Sending Slack notification: {}", message);
    }
}
