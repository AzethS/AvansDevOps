package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.user.Role;

/**
 * State Pattern (Behavioral)
 * Observer Pattern (Behavioral)
 */
public class TestedState extends BacklogItemState {
    protected TestedState(BacklogItem context, BacklogItemStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void onStateChanged() {
        super.onStateChanged();
        this.context.getSprint().notifySubscribers(
                String.format("Backlog item '%s' needs to be checked via the definition of done", this.context.getTitle()),
                user -> user.getRole() == Role.LEAD_DEVELOPER
        );
    }

    @Override
    public void transferToReadyForTesting() {
        this.setState(BacklogItemStateType.READY_FOR_TESTING);
    }

    @Override
    public void transferToDone() {
        this.setState(BacklogItemStateType.DONE);
    }
}
