package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.user.Role;

/**
 * State Pattern (Behavioral)
 */
public class ReadyForTestingState extends BacklogItemState {
    protected ReadyForTestingState(BacklogItem context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        System.out.println("State changed to ReadyForTesting");

        this.context.getSprint().notifySubscribers(
                String.format("Backlog item '%s' is ready to be tested", this.context.getTitle()),
                (user) -> user.getRole() == Role.TESTER
        );
    }

    @Override
    public void transferToTodo() {
        throw new UnsupportedOperationException("Cannot transfer from ReadyForTesting to Todo");
    }

    @Override
    public void transferToDoing() {
        throw new UnsupportedOperationException("Cannot transfer from ReadyForTesting to Doing");
    }

    @Override
    public void transferToReadyForTesting() {
        throw new UnsupportedOperationException("Already in ReadyForTesting state");
    }

    @Override
    public void transferToTesting() {
        this.context.setState(new TestingState(this.context));
    }

    @Override
    public void transferToTested() {
        throw new UnsupportedOperationException("Cannot transfer from ReadyForTesting to Tested");
    }

    @Override
    public void transferToDone() {
        throw new UnsupportedOperationException("Cannot transfer from ReadyForTesting to Done");
    }
}
