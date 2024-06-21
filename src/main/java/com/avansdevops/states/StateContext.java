package com.avansdevops.states;

/**
 * State Pattern (Behavioral)
 */
public interface StateContext<S> {
    void setState(S state);

    S getState();
}
