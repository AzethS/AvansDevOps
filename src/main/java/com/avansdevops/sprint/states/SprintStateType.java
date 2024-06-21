package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.states.StateType;

/**
 * State Pattern (Behavioral)
 * Factory Pattern (Creational)
 */
public enum SprintStateType implements StateType<SprintState, Sprint> {
    PLANNED(PlannedState::new),
    IN_PROGRESS(InProgressState::new),
    IN_REVIEW(InReviewState::new),
    FINISHED(FinishedState::new),
    FAILED(FailedState::new);

    private final SprintStateFactory factory;

    SprintStateType(SprintStateFactory factory) {
        this.factory = factory;
    }

    @Override
    public SprintState create(Sprint context) {
        return this.factory.create(context, this);
    }

    @FunctionalInterface
    public interface SprintStateFactory {
        SprintState create(Sprint context, SprintStateType stateType);
    }
}
