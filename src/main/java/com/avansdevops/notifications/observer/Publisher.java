package com.avansdevops.notifications.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Observer pattern (Behavioral)
 */
public abstract class Publisher<T extends Subscriber> {
    protected final List<T> subscribers = new ArrayList<>();

    public void subscribe(T subscriber) {
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(T subscriber) {
        this.subscribers.remove(subscriber);
    }

    public List<T> getSubscribers() {
        return this.subscribers;
    }

    public void notifySubscribers(String message) {
        for (T subscriber : this.subscribers) {
            subscriber.update(message);
        }
    }

    public void notifySubscribers(String message, Predicate<T> predicate) {
        for (T subscriber : this.subscribers) {
            if (predicate.test(subscriber)) {
                subscriber.update(message);
            }
        }
    }
}
