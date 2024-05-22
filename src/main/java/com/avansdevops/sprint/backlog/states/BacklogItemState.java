package com.avansdevops.sprint.backlog.states;

import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.states.State;

/**
 * State Pattern (Behavioral)
 */
public abstract class BacklogItemState extends State<BacklogItemState, BacklogItem> {
    protected BacklogItemState(BacklogItem context, BacklogItemStateType state) {
        super(context, state);
    }

    public void transferToTodo() {
        this.throwCannotTransfer(BacklogItemStateType.TODO);
    }

    public void transferToDoing() {
        this.throwCannotTransfer(BacklogItemStateType.DOING);
    }

    public void transferToReadyForTesting() {
        this.throwCannotTransfer(BacklogItemStateType.READY_FOR_TESTING);
    }

    public void transferToTesting() {
        this.throwCannotTransfer(BacklogItemStateType.TESTING);
    }

    public void transferToTested() {
        this.throwCannotTransfer(BacklogItemStateType.TESTED);
    }

    public void transferToDone() {
        this.throwCannotTransfer(BacklogItemStateType.DONE);
    }
}
