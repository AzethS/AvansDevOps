package com.avansdevops.sprint.backlog.states;

import com.avansdevops.AvansDevOps;
import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.user.Role;

/**
 * State Pattern (Behavioral)
 */
public class TestedState extends BacklogItemState {
    protected TestedState(BacklogItem context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        AvansDevOps.LOGGER.info("State changed to Tested");

        this.context.getSprint().notifySubscribers(
                String.format("Backlog item '%s' needs to be checked via the definition of done", this.context.getTitle()),
                user -> user.getRole() == Role.LEAD_DEVELOPER
        );
    }

    @Override
    public void transferToTodo() {
        throw new IllegalStateException("Cannot transfer from Tested to Todo");
    }

    @Override
    public void transferToDoing() {
        throw new IllegalStateException("Cannot transfer from Tested to Doing");
    }

    @Override
    public void transferToReadyForTesting() {
        this.context.setState(new ReadyForTestingState(this.context));
    }

    @Override
    public void transferToTesting() {
        throw new IllegalStateException("Cannot transfer from Tested to Testing");
    }

    @Override
    public void transferToTested() {
        throw new IllegalStateException("Already in Tested state");
    }

    @Override
    public void transferToDone() {
        this.context.setState(new DoneState(this.context));
    }
}
