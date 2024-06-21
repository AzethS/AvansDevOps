package com.avansdevops.states;

/**
 * Factory Pattern (Creational)
 */
public interface StateType<S, C extends StateContext<S>> {
    S create(C context);
}
