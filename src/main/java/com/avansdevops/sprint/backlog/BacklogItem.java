package com.avansdevops.sprint.backlog;

import com.avansdevops.sprint.backlog.states.BacklogItemState;
import com.avansdevops.sprint.backlog.states.TodoState;

/**
 * State Pattern (Behavioral)
 */
public class BacklogItem {
    private BacklogItemState state = new TodoState(this);

    public void setState(BacklogItemState state) {
        this.state = state;
    }

    public BacklogItemState getState() {
        return this.state;
    }
}
