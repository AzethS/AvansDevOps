package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.states.StateType;

public enum BacklogItemStateType implements StateType<BacklogItemState, BacklogItem> {
    TODO(TodoState::new),
    DOING(DoingState::new),
    READY_FOR_TESTING(ReadyForTestingState::new),
    TESTING(TestingState::new),
    TESTED(TestedState::new),
    DONE(DoneState::new);

    private final BacklogItemStateFactory factory;

    BacklogItemStateType(BacklogItemStateFactory factory) {
        this.factory = factory;
    }

    @Override
    public BacklogItemState create(BacklogItem context) {
        return this.factory.create(context, this);
    }

    @FunctionalInterface
    public interface BacklogItemStateFactory {
        BacklogItemState create(BacklogItem context, BacklogItemStateType stateType);
    }
}
