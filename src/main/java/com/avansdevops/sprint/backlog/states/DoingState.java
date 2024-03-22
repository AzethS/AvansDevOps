package com.avansdevops.sprint.backlog.states;

import com.avansdevops.AvansDevOps;
import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 * Observer Pattern (Behavioral)
 */
public class DoingState extends BacklogItemState {
    protected DoingState(BacklogItem context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        AvansDevOps.LOGGER.info("State changed to Doing");
    }

    @Override
    public void transferToTodo() {
        this.context.setState(new TodoState(this.context));
    }

    @Override
    public void transferToDoing() {
        throw new IllegalStateException("Already in Doing state");
    }

    @Override
    public void transferToReadyForTesting() {
        this.context.setState(new ReadyForTestingState(this.context));
    }

    @Override
    public void transferToTesting() {
        throw new IllegalStateException("Cannot transfer from Doing to Testing");
    }

    @Override
    public void transferToTested() {
        throw new IllegalStateException("Cannot transfer from Doing to Tested");
    }

    @Override
    public void transferToDone() {
        throw new IllegalStateException("Cannot transfer from Doing to Done");
    }
}
