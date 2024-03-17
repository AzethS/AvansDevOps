package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public abstract class SprintState {
    protected final Sprint context;

    protected SprintState(Sprint context) {
        this.context = context;
    }

    public abstract void onStateChange();

    public abstract void transferToPlanned();

    public abstract void transferToInProgress();

    public abstract void transferToInReview();

    public abstract void transferToFinished();

    public abstract void transferToFailed();
}