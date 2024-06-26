package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.user.Role;

/**
 * State Pattern (Behavioral)
 * Observer Pattern (Behavioral)
 */
public class ReadyForTestingState extends BacklogItemState {
    protected ReadyForTestingState(BacklogItem context, BacklogItemStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void onStateChanged() { // Complexity 2
        super.onStateChanged();
        this.context.getSprint().notifySubscribers(
                String.format("Backlog item '%s' is ready to be tested", this.context.getTitle()),
                user -> user.getRole() == Role.TESTER // +1 (condition in lambda)
        );
    }

    @Override
    public void transferToTesting() {
        this.setState(BacklogItemStateType.TESTING);
    }
}
