package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public class InReviewState extends SprintState {
    protected InReviewState(Sprint context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        System.out.println("State changed to InReview");
    }

    @Override
    public void transferToPlanned() {
        throw new UnsupportedOperationException("Cannot transfer from InReview to Planned");
    }

    @Override
    public void transferToInProgress() {
        throw new UnsupportedOperationException("Cannot transfer from InReview to InProgress");
    }

    @Override
    public void transferToInReview() {
        throw new UnsupportedOperationException("Already in InReview state");
    }

    @Override
    public void transferToFinished() {
        this.context.setState(new FinishedState(this.context));
    }

    @Override
    public void transferToFailed() {
        this.context.setState(new FailedState(this.context));
    }
}
