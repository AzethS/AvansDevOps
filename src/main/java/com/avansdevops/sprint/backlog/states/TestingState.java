package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.user.Role;

/**
 * State Pattern (Behavioral)
 * Observer Pattern (Behavioral)
 */
public class TestingState extends BacklogItemState {
    protected TestingState(BacklogItem context, BacklogItemStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void transferToTodo() { // Complexity 2
        this.setState(BacklogItemStateType.TODO);

        this.context.getSprint().notifySubscribers(
                String.format("Backlog item '%s' has failed the testing phase", this.context.getTitle()),
                user -> user.getRole() == Role.SCRUM_MASTER // +1 (condition in lambda)
        );
    }

    @Override
    public void transferToTested() {
        this.setState(BacklogItemStateType.TESTED);
    }
}
