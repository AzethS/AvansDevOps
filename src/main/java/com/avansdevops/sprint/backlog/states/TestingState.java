package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 * Observer Pattern (Behavioral)
 */
public class TestingState extends BacklogItemState {
    protected TestingState(BacklogItem context) {
        super(context);
    }

    @Override
    public void transferToTodo() {
        System.out.println("Transferring from Testing to Todo");
        this.context.setState(new TodoState(this.context));
        // TODO: NOTIFY SCRUM MASTER
    }

    @Override
    public void transferToDoing() {
        throw new UnsupportedOperationException("Cannot transfer from Testing to Doing");
    }

    @Override
    public void transferToReadyForTesting() {
        throw new UnsupportedOperationException("Cannot transfer from Testing to ReadyForTesting");
    }

    @Override
    public void transferToTesting() {
        throw new UnsupportedOperationException("Already in Testing state");
    }

    @Override
    public void transferToTested() {
        System.out.println("Transferring from Testing to Tested");
        this.context.setState(new TestedState(this.context));
        // TODO: NOTIFY LEAD DEVELOPERS
    }

    @Override
    public void transferToDone() {
        throw new UnsupportedOperationException("Cannot transfer from Testing to Done");
    }
}
