package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.Activity;
import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
 * Observer Pattern (Behavioral)
 */
public class DoingState extends BacklogItemState {
    protected DoingState(BacklogItem context, BacklogItemStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void transferToTodo() {
        this.setState(BacklogItemStateType.TODO);
    }

    @Override
    public void transferToReadyForTesting() {
        if (this.isFinished()) {
            this.setState(BacklogItemStateType.READY_FOR_TESTING);
        } else {
            throw new IllegalStateException("Not all activities have been finished!");
        }
    }

    private boolean isFinished() {
        for (Activity activity : this.context.getActivities()) {
            if (!activity.isFinished()) {
                return false;
            }
        }
        return true;
    }
}
