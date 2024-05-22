package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;


public class PlannedState extends AbstractSprintState {
    public PlannedState(Sprint context, SprintState state) {
        super(context, state);
    }

    @Override
    public void transferToInProgress() {
        this.setState(SprintState.IN_PROGRESS);
    }
}
