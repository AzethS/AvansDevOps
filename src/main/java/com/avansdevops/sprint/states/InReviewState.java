package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public class InReviewState extends SprintState {
    protected InReviewState(Sprint context, SprintStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void transferToFinished() {
        this.setState(SprintStateType.FINISHED);
    }

    @Override
    public void transferToFailed() {
        this.setState(SprintStateType.FAILED);
    }
}
