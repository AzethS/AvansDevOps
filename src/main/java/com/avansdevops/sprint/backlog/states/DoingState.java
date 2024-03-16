package com.avansdevops.sprint.backlog.states;

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
    public void transferToTodo() {
        System.out.println("Transferring from Doing to Todo");
        this.context.setState(new TodoState(this.context));
    }

    @Override
    public void transferToDoing() {
        throw new UnsupportedOperationException("Already in Doing state");
    }

    @Override
    public void transferToReadyForTesting() {
        System.out.println("Transferring from Doing to ReadyForTesting");
        this.context.setState(new ReadyForTestingState(this.context));
        // TODO: NOTIFY TESTERS
    }

    @Override
    public void transferToTesting() {
        throw new UnsupportedOperationException("Cannot transfer from Doing to Testing");
    }

    @Override
    public void transferToTested() {
        throw new UnsupportedOperationException("Cannot transfer from Doing to Tested");
    }

    @Override
    public void transferToDone() {
        throw new UnsupportedOperationException("Cannot transfer from Doing to Done");
    }
}
