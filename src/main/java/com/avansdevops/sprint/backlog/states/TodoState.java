package com.avansdevops.sprint.backlog.states;

import com.avansdevops.AvansDevOps;
import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 */
public class TodoState extends BacklogItemState {
    public TodoState(BacklogItem context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        AvansDevOps.LOGGER.info("State changed to Todo");
    }

    @Override
    public void transferToTodo() {
        throw new IllegalStateException("Already in Todo state");
    }

    @Override
    public void transferToDoing() {
        this.context.setState(new DoingState(this.context));
    }

    @Override
    public void transferToReadyForTesting() {
        throw new IllegalStateException("Cannot transfer from Todo to ReadyForTesting");
    }

    @Override
    public void transferToTesting() {
        throw new IllegalStateException("Cannot transfer from Todo to Testing");
    }

    @Override
    public void transferToTested() {
        throw new IllegalStateException("Cannot transfer from Todo to Tested");
    }

    @Override
    public void transferToDone() {
        throw new IllegalStateException("Cannot transfer from Todo to Done");
    }
}
