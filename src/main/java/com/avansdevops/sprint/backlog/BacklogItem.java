package com.avansdevops.sprint.backlog;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.states.BacklogItemState;
import com.avansdevops.sprint.backlog.states.BacklogItemStateType;
import com.avansdevops.sprint.backlog.states.DoneState;
import com.avansdevops.states.StateContext;
import com.avansdevops.user.User;

/**
 * State Pattern (Behavioral)
 */
public class BacklogItem implements StateContext<BacklogItemState> {
    private final Sprint sprint;
    private final String title;
    private User assignedUser;
    private BacklogItemState state = BacklogItemStateType.TODO.create(this);

    public BacklogItem(Sprint sprint, String title) {
        this.sprint = sprint;
        this.title = title;
    }

    @Override
    public void setState(BacklogItemState state) {
        this.state = state;
    }

    @Override
    public BacklogItemState getState() {
        return this.state;
    }

    public void setAssignedUser(User user) {
        this.assignedUser = user;
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

    public User getAssignedUser() {
        return this.assignedUser;
    }
}
