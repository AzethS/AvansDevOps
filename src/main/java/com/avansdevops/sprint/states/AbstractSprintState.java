package com.avansdevops.sprint.states;

import com.avansdevops.AvansDevOps;
import com.avansdevops.sprint.Sprint;

public abstract class AbstractSprintState {
    protected final SprintState state;
    protected final Sprint context;

    protected AbstractSprintState(Sprint context, SprintState state) {
        this.context = context;
        this.state = state;
    }

    protected final void setState(SprintState state) {
        this.context.setState(state.create(this.context));
    }

    public void onStateChange() {
        AvansDevOps.LOGGER.info("State changed to {}", this.state);
    }

    public void transferToPlanned() {
        this.throwCannotTransfer(SprintState.PLANNED);
    }

    public void transferToInProgress() {
        this.throwCannotTransfer(SprintState.IN_PROGRESS);
    }

    public void transferToInReview() {
        this.throwCannotTransfer(SprintState.IN_REVIEW);
    }

    public void transferToFinished() {
        this.throwCannotTransfer(SprintState.FINISHED);
    }

    public void transferToFailed() {
        this.throwCannotTransfer(SprintState.FAILED);
    }

    private void throwCannotTransfer(SprintState newState) {
        if (this.state == newState) {
            throw new IllegalStateException("Already in %s state".formatted(newState));
        } else {
            throw new IllegalStateException("Cannot transfer from %s to %s".formatted(this.state, newState));
        }
    }
}