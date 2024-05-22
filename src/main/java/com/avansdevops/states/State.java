package com.avansdevops.states;

import com.avansdevops.AvansDevOps;

public class State<S extends State<S, C>, C extends StateContext<S>> {
    private final StateType<S, C> stateType;
    protected final C context;

    protected State(C context, StateType<S, C> stateType) {
        this.context = context;
        this.stateType = stateType;
    }

    protected final void setState(StateType<S, C> stateType) {
        S newState = stateType.create(this.context);
        this.context.setState(newState);
        newState.onStateChanged();
    }

    protected void onStateChanged() {
        AvansDevOps.LOGGER.info("State changed to {}", this.stateType);
    }

    protected void throwCannotTransfer(StateType<S, C> newState) {
        if (this.stateType == newState) {
            throw new IllegalStateException("Already in %s state".formatted(newState));
        } else {
            throw new IllegalStateException("Cannot transfer from %s to %s".formatted(this.stateType, newState));
        }
    }
}
