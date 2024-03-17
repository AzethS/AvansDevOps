package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 */
public class DoneState extends BacklogItemState {
    protected DoneState(BacklogItem context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        System.out.println("State changed to Done");
    }

    @Override
    public void transferToTodo() {
        throw new UnsupportedOperationException("Cannot transfer from Done to Todo");
    }

    @Override
    public void transferToDoing() {
        throw new UnsupportedOperationException("Cannot transfer from Done to Doing");
    }

    @Override
    public void transferToReadyForTesting() {
        throw new UnsupportedOperationException("Cannot transfer from Done to ReadyForTesting");
    }

    @Override
    public void transferToTesting() {
        throw new UnsupportedOperationException("Cannot transfer from Done to Testing");
    }

    @Override
    public void transferToTested() {
        throw new UnsupportedOperationException("Cannot transfer from Done to Tested");
    }

    @Override
    public void transferToDone() {
        throw new UnsupportedOperationException("Already in Done state");
    }
}
