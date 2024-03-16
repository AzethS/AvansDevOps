package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 */
public class TodoState extends BacklogItemState {
    public TodoState(BacklogItem context) {
        super(context);
    }

    @Override
    public void transferToTodo() {
        throw new UnsupportedOperationException("Already in Todo state");
    }

    @Override
    public void transferToDoing() {
        System.out.println("Transferring from Todo to Doing");
        this.context.setState(new DoingState(this.context));
    }

    @Override
    public void transferToReadyForTesting() {
        throw new UnsupportedOperationException("Cannot transfer from Todo to ReadyForTesting");
    }

    @Override
    public void transferToTesting() {
        throw new UnsupportedOperationException("Cannot transfer from Todo to Testing");
    }

    @Override
    public void transferToTested() {
        throw new UnsupportedOperationException("Cannot transfer from Todo to Tested");
    }

    @Override
    public void transferToDone() {
        throw new UnsupportedOperationException("Cannot transfer from Todo to Done");
    }
}
