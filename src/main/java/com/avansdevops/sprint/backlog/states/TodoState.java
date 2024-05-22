package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 */
public class TodoState extends BacklogItemState {
    public TodoState(BacklogItem context, BacklogItemStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void transferToDoing() {
        this.setState(BacklogItemStateType.DOING);
    }
}
