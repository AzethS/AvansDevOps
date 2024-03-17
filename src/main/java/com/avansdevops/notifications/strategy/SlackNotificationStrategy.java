package com.avansdevops.notifications.strategy;

public class SlackNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        System.out.printf("Slack: %s%n", message);
    }
}
