package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 */
public abstract class BacklogItemState {
    protected final BacklogItem context;

    protected BacklogItemState(BacklogItem context) {
        this.context = context;
    }

    public abstract void transferToTodo();

    public abstract void transferToDoing();

    public abstract void transferToReadyForTesting();

    public abstract void transferToTesting();

    public abstract void transferToTested();

    public abstract void transferToDone();
}
