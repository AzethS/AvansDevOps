package com.avansdevops.notifications.strategy;

public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        System.out.printf("Email: %s%n", message);
    }
}
