package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.user.Role;

public class FailedState extends SprintState {
    protected FailedState(Sprint context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        System.out.println("State changed to Failed");

        this.context.notifySubscribers(
                "Sprint has failed",
                (user) -> user.getRole() == Role.PRODUCT_OWNER || user.getRole() == Role.SCRUM_MASTER
        );
    }

    @Override
    public void transferToPlanned() {
        throw new UnsupportedOperationException("Cannot transfer from Failed to Planned");
    }

    @Override
    public void transferToInProgress() {
        throw new UnsupportedOperationException("Cannot transfer from Failed to InProgress");
    }

    @Override
    public void transferToInReview() {
        throw new UnsupportedOperationException("Cannot transfer from Failed to InReview");
    }

    @Override
    public void transferToFinished() {
        throw new UnsupportedOperationException("Cannot transfer from Failed to Finished");
    }

    @Override
    public void transferToFailed() {
        throw new UnsupportedOperationException("Already in Failed state");
    }
}
