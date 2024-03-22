package com.avansdevops.sprint.states;

import com.avansdevops.AvansDevOps;
import com.avansdevops.sprint.Sprint;

public class InProgressState extends SprintState {
    protected InProgressState(Sprint context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        AvansDevOps.LOGGER.info("State changed to InProgress");
    }

    @Override
    public void transferToPlanned() {
        throw new IllegalStateException("Cannot transfer from InProgress to Planned");
    }

    @Override
    public void transferToInProgress() {
        throw new IllegalStateException("Already in InProgress state");
    }

    @Override
    public void transferToInReview() {
        this.context.setState(new InReviewState(this.context));
    }

    @Override
    public void transferToFinished() {
        this.context.setState(new FinishedState(this.context));
    }

    @Override
    public void transferToFailed() {
        throw new IllegalStateException("Cannot transfer from InProgress to Failed");
    }
}
