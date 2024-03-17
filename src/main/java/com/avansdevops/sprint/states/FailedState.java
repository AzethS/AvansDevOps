package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.user.Role;

public class FailedState extends SprintState {
    protected FailedState(Sprint context) {
        super(context);

        this.context.notifyObservers(
                "Sprint has failed",
                (role) -> role == Role.PRODUCT_OWNER || role == Role.SCRUM_MASTER
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
