package com.avansdevops.sprint.states;

import com.avansdevops.AvansDevOps;
import com.avansdevops.sprint.Sprint;


public class PlannedState extends SprintState {
    public PlannedState(Sprint context) {
        super(context);
    }

    @Override
    public void onStateChange() {
        AvansDevOps.LOGGER.info("State changed to Planned");
    }

    @Override
    public void transferToPlanned() {
        throw new IllegalStateException("Already in Planned state");
    }

    @Override
    public void transferToInProgress() {
        this.context.setState(new InProgressState(this.context));
    }

    @Override
    public void transferToInReview() {
        throw new IllegalStateException("Cannot transfer from Planned to InReview");
    }

    @Override
    public void transferToFinished() {
        throw new IllegalStateException("Cannot transfer from Planned to Finished");
    }

    @Override
    public void transferToFailed() {
        throw new IllegalStateException("Cannot transfer from Planned to Failed");
    }
}
