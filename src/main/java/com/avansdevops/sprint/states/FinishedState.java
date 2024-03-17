package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public class FinishedState extends SprintState {
    protected FinishedState(Sprint context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        System.out.println("State changed to Finished");
    }

    @Override
    public void transferToPlanned() {
        throw new UnsupportedOperationException("Cannot transfer from Finished to Planned");
    }

    @Override
    public void transferToInProgress() {
        throw new UnsupportedOperationException("Cannot transfer from Finished to InProgress");
    }

    @Override
    public void transferToInReview() {
        throw new UnsupportedOperationException("Cannot transfer from Finished to InReview");
    }

    @Override
    public void transferToFinished() {
        throw new UnsupportedOperationException("Already in Finished state");
    }

    @Override
    public void transferToFailed() {
        throw new UnsupportedOperationException("Cannot transfer from Finished to Failed");
    }
}
