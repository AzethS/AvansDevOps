package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.user.Role;

/**
 * State Pattern (Behavioral)
 * Observer Pattern (Behavioral)
 */
public class TestingState extends BacklogItemState {
    protected TestingState(BacklogItem context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        System.out.println("State changed to Testing");
    }

    @Override
    public void transferToTodo() {
        this.context.setState(new TodoState(this.context));

        this.context.getSprint().notifySubscribers(
                String.format("Backlog item '%s' has failed the testing phase", this.context.getTitle()),
                (user) -> user.getRole() == Role.SCRUM_MASTER
        );
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
        this.context.setState(new TestedState(this.context));
    }

    @Override
    public void transferToDone() {
        throw new UnsupportedOperationException("Cannot transfer from Testing to Done");
    }
}
