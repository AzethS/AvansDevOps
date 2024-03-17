package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public class InProgressState extends SprintState {
    protected InProgressState(Sprint context) {
        super(context);
    }

    @Override
    public void transferToPlanned() {
        throw new UnsupportedOperationException("Cannot transfer from InProgress to Planned");
    }

    @Override
    public void transferToInProgress() {
        throw new UnsupportedOperationException("Already in InProgress state");
    }

    @Override
    public void transferToInReview() {
        System.out.println("Transferring from InProgress to InReview");
        this.context.setState(new InReviewState(this.context));
    }

    @Override
    public void transferToFinished() {
        System.out.println("Transferring from InProgress to Finished");
        this.context.setState(new FinishedState(this.context));
    }

    @Override
    public void transferToFailed() {
        throw new UnsupportedOperationException("Cannot transfer from InProgress to Failed");
    }
}
