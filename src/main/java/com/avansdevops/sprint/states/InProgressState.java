package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public class InProgressState extends AbstractSprintState {
    protected InProgressState(Sprint context, SprintState state) {
        super(context, state);
    }

    @Override
    public void transferToInReview() {
        this.setState(SprintState.IN_REVIEW);
    }

    @Override
    public void transferToFinished() {
        this.setState(SprintState.FINISHED);
    }
}
