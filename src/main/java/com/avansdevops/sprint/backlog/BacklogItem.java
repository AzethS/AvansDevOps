package com.avansdevops.sprint.backlog;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.states.BacklogItemState;
import com.avansdevops.sprint.backlog.states.DoneState;
import com.avansdevops.sprint.backlog.states.TodoState;
import com.avansdevops.user.User;

/**
 * State Pattern (Behavioral)
 */
public class BacklogItem {
    private final Sprint sprint;
    private final String title;
    private User assignedUser;
    private BacklogItemState state = new TodoState(this);

    public BacklogItem(Sprint sprint, String title) {
        this.sprint = sprint;
        this.title = title;
    }

    public void setState(BacklogItemState state) {
        this.state = state;
        state.onStateChange();
    }

    public void setAssignedUser(User user) {
        this.assignedUser = user;
    }

    public boolean isDone() {
        return this.state instanceof DoneState;
    }

    public BacklogItemState getState() {
        return this.state;
    }

    public Sprint getSprint() {
        return this.sprint;
    }

    public String getTitle() {
        return this.title;
    }

    public User getAssignedUser() {
        return this.assignedUser;
    }
}
