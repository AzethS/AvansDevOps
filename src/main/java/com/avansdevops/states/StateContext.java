package com.avansdevops.states;

public interface StateContext<S> {
    void setState(S state);

    S getState();
}
