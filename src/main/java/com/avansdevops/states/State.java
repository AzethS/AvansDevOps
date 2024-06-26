package com.avansdevops.states;

import com.avansdevops.AvansDevOps;

/**
 * State Pattern (Behavioral)
 */
public class State<S extends State<S, C>, C extends StateContext<S>> {
    private final StateType<S, C> type;
    protected final C context;

    protected State(C context, StateType<S, C> type) {
        this.context = context;
        this.type = type;
    }

    protected final void setState(StateType<S, C> stateType) {
        S newState = stateType.create(this.context);
        this.context.setState(newState);
        newState.onStateChanged();
    }

    protected void onStateChanged() {
        AvansDevOps.LOGGER.info("State changed to {}", this.type);
    }

    protected void throwCannotTransfer(StateType<S, C> newState) { // Complexity 2
        if (this.type == newState) { // +1 (if statement)
            throw new IllegalStateException("Already in %s state".formatted(newState));
        } else {
            throw new IllegalStateException("Cannot transfer from %s to %s".formatted(this.type, newState));
        }
    }

    public StateType<S, C> getType() {
        return this.type;
    }
}
