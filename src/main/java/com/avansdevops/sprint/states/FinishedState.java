package com.avansdevops.sprint.states;

import com.avansdevops.AvansDevOps;
import com.avansdevops.sprint.Sprint;

public class FinishedState extends SprintState {
    protected FinishedState(Sprint context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        AvansDevOps.LOGGER.info("State changed to Finished");
        this.context.onFinished();
    }

    @Override
    public void transferToPlanned() {
        throw new IllegalStateException("Cannot transfer from Finished to Planned");
    }

    @Override
    public void transferToInProgress() {
        throw new IllegalStateException("Cannot transfer from Finished to InProgress");
    }

    @Override
    public void transferToInReview() {
        throw new IllegalStateException("Cannot transfer from Finished to InReview");
    }

    @Override
    public void transferToFinished() {
        throw new IllegalStateException("Already in Finished state");
    }

    @Override
    public void transferToFailed() {
        throw new IllegalStateException("Cannot transfer from Finished to Failed");
    }
}
