package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.Activity;
import com.avansdevops.sprint.backlog.BacklogItem;

/**
 * State Pattern (Behavioral)
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
    public void transferToReadyForTesting() { // Complexity 2
        if (this.isFinished()) { // +1 (if statement)
            this.setState(BacklogItemStateType.READY_FOR_TESTING);
        } else {
            throw new IllegalStateException("Not all activities have been finished!");
        }
    }

    private boolean isFinished() { // Complexity 3
        for (Activity activity : this.context.getActivities()) { // +1 (loop)
            if (!activity.isFinished()) { // +1 (if statement)
                return false;
            }
        }
        return true;
    }
}
