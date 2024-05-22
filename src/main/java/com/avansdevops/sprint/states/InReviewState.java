package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public class InReviewState extends AbstractSprintState {
    protected InReviewState(Sprint context, SprintState state) {
        super(context, state);
    }

    @Override
    public void transferToFinished() {
        this.setState(SprintState.FINISHED);
    }

    @Override
    public void transferToFailed() {
        this.setState(SprintState.FAILED);
    }
}
