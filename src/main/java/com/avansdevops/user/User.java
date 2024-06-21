package com.avansdevops.user;

import com.avansdevops.notifications.observer.Subscriber;
import com.avansdevops.notifications.strategy.EmailNotificationStrategy;
import com.avansdevops.notifications.strategy.NotificationStrategy;

/**
 * Observer Pattern (Behavioral)
 * Strategy Pattern (Behavioral)
 */
public class User implements Subscriber {
    private final String name;
    private Role role;
    private NotificationStrategy notificationStrategy;

    public User(String name) {
        this(name, Role.UNKNOWN);
    }

    public User(String name, Role role) {
        this(name, role, new EmailNotificationStrategy());
    }

    public User(String name, Role role, NotificationStrategy notificationStrategy) {
        this.name = name;
        this.role = role;
        this.notificationStrategy = notificationStrategy;
    }

    public String getName() {
        return this.name;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    @Override
    public void update(String message) {
        this.notificationStrategy.sendNotification(message);
    }

    @Override
    public String toString() {
        return String.format("User{name=%s,role=%s}", this.name, this.role);
    }
}
