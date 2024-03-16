package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 */
public class TestedState extends BacklogItemState {
    protected TestedState(BacklogItem context) {
        super(context);
    }

    @Override
    public void transferToTodo() {
        throw new UnsupportedOperationException("Cannot transfer from Tested to Todo");
    }

    @Override
    public void transferToDoing() {
        throw new UnsupportedOperationException("Cannot transfer from Tested to Doing");
    }

    @Override
    public void transferToReadyForTesting() {
        System.out.println("Transferring from Tested to ReadyForTesting");
        this.context.setState(new ReadyForTestingState(this.context));
    }

    @Override
    public void transferToTesting() {
        throw new UnsupportedOperationException("Cannot transfer from Tested to Testing");
    }

    @Override
    public void transferToTested() {
        throw new UnsupportedOperationException("Already in Tested state");
    }

    @Override
    public void transferToDone() {
        System.out.println("Transferring from Tested to Done");
        this.context.setState(new DoneState(this.context));
    }
}
