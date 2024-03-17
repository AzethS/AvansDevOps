package com.avansdevops.sprint.backlog;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.states.BacklogItemState;
import com.avansdevops.sprint.backlog.states.DoneState;
import com.avansdevops.sprint.backlog.states.TodoState;

/**
 * State Pattern (Behavioral)
 */
public class BacklogItem {
    private final Sprint sprint;
    private final String title;
    private BacklogItemState state = new TodoState(this);

    public BacklogItem(Sprint sprint, String title) {
        this.sprint = sprint;
        this.title = title;
    }

    public void setState(BacklogItemState state) {
        this.state = state;
        state.onStateChange();
    }

    public BacklogItemState getState() {
        return this.state;
    }

    public boolean isDone() {
        return this.state instanceof DoneState;
    }

    public Sprint getSprint() {
        return this.sprint;
    }

    public String getTitle() {
        return this.title;
    }
}
