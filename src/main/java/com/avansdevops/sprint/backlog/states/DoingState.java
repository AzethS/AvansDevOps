package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 * Observer Pattern (Behavioral)
 */
public class DoingState extends BacklogItemState {
    protected DoingState(BacklogItem context, BacklogItemStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void transferToTodo() {
        this.setState(BacklogItemStateType.TODO);
    }

    @Override
    public void transferToReadyForTesting() {
        this.setState(BacklogItemStateType.READY_FOR_TESTING);
    }
}
