package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public class InProgressState extends SprintState {
    protected InProgressState(Sprint context, SprintStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void transferToInReview() {
        this.setState(SprintStateType.IN_REVIEW);
    }

    @Override
    public void transferToFinished() {
        this.setState(SprintStateType.FINISHED);
    }
}
