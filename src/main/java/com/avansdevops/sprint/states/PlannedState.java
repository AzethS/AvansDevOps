package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;


public class PlannedState extends SprintState {
    public PlannedState(Sprint context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        System.out.println("State changed to Planned");
    }

    @Override
    public void transferToPlanned() {
        throw new UnsupportedOperationException("Already in Planned state");
    }

    @Override
    public void transferToInProgress() {
        this.context.setState(new InProgressState(this.context));
    }

    @Override
    public void transferToInReview() {
        throw new UnsupportedOperationException("Cannot transfer from Planned to InReview");
    }

    @Override
    public void transferToFinished() {
        throw new UnsupportedOperationException("Cannot transfer from Planned to Finished");
    }

    @Override
    public void transferToFailed() {
        throw new UnsupportedOperationException("Cannot transfer from Planned to Failed");
    }
}
