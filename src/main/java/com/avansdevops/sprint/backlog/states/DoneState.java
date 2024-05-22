package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 */
public class DoneState extends BacklogItemState {
    protected DoneState(BacklogItem context, BacklogItemStateType stateType) {
        super(context, stateType);
    }
}
