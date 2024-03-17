package com.avansdevops.notifications.strategy;

public class SmsNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        System.out.printf("Sms: %s%n", message);
    }
}
