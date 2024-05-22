package com.avansdevops.states;

public interface StateType<S, C extends StateContext<S>> {
    S create(C context);
}
