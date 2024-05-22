package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;

public enum SprintState {
    PLANNED(PlannedState::new),
    IN_PROGRESS(InProgressState::new),
    IN_REVIEW(InReviewState::new),
    FINISHED(FinishedState::new),
    FAILED(FailedState::new);

    private final SprintStateFactory factory;

    SprintState(SprintStateFactory factory) {
        this.factory = factory;
    }

    public AbstractSprintState create(Sprint context) {
        return this.factory.create(context, this);
    }

    @FunctionalInterface
    public interface SprintStateFactory {
        AbstractSprintState create(Sprint context, SprintState state);
    }
}
