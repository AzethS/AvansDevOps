package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.states.State;

public abstract class SprintState extends State<SprintState, Sprint> {
    protected SprintState(Sprint context, SprintStateType state) {
        super(context, state);
    }

    public void transferToPlanned() {
        this.throwCannotTransfer(SprintStateType.PLANNED);
    }

    public void transferToInProgress() {
        this.throwCannotTransfer(SprintStateType.IN_PROGRESS);
    }

    public void transferToInReview() {
        this.throwCannotTransfer(SprintStateType.IN_REVIEW);
    }

    public void transferToFinished() {
        this.throwCannotTransfer(SprintStateType.FINISHED);
    }

    public void transferToFailed() {
        this.throwCannotTransfer(SprintStateType.FAILED);
    }
}