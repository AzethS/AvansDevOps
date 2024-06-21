package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

/**
 * State Pattern (Behavioral)
 */
public class FinishedState extends SprintState {
    protected FinishedState(Sprint context, SprintStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void onStateChanged() {
        super.onStateChanged();
        this.context.onFinished();
    }
}
