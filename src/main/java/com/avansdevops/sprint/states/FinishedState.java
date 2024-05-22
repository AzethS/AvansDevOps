package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public class FinishedState extends AbstractSprintState {
    protected FinishedState(Sprint context, SprintState state) {
        super(context, state);
    }

    @Override
    public void onStateChange() {
        super.onStateChange();
        this.context.onFinished();
    }
}
