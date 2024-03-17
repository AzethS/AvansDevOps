package com.avansdevops.notifications.observer;

/**
 * Observer pattern (Behavioral)
 */
public interface Subscriber {
    void update(String message);
}
