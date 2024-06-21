package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

/**
 * State Pattern (Behavioral)
 */
public class PlannedState extends SprintState {
    public PlannedState(Sprint context, SprintStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void transferToInProgress() {
        this.setState(SprintStateType.IN_PROGRESS);
    }
}
