package com.avansdevops.sprint.backlog;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.states.BacklogItemState;
import com.avansdevops.sprint.backlog.states.BacklogItemStateType;
import com.avansdevops.sprint.states.SprintStateType;
import com.avansdevops.states.StateContext;
import com.avansdevops.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * State Pattern (Behavioral)
 */
public class BacklogItem implements StateContext<BacklogItemState> {
    private final List<Activity> activities = new ArrayList<>();
    private final String title;
    private Sprint sprint;
    private User assignedUser;
    private BacklogItemState state = BacklogItemStateType.TODO.create(this);

    public BacklogItem(String title) {
        this.title = title;
    }

    @Override
    public void setState(BacklogItemState state) {
        this.ensureSprintInState(SprintStateType.IN_PROGRESS);
        this.state = state;
    }

    @Override
    public BacklogItemState getState() {
        return this.state;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public void setAssignedUser(User user) { // Complexity 2
        if (!user.getRole().isDeveloper()) { // +1 (if statement)
            throw new IllegalArgumentException("User is not a developer!");
        }

        this.assignedUser = user;
    }

    public boolean isDone() {
        return this.state.getType() == BacklogItemStateType.DONE;
    }

    public Sprint getSprint() {
        this.ensureHasSprint();
        return this.sprint;
    }

    public String getTitle() {
        return this.title;
    }

    public User getAssignedUser() {
        return this.assignedUser;
    }

    public List<Activity> getActivities() {
        return this.activities;
    }

    public void addActivity(Activity activity) {
        this.ensureSprintInState(SprintStateType.PLANNED);
        this.activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        this.ensureSprintInState(SprintStateType.PLANNED);
        this.activities.remove(activity);
    }

    private void ensureSprintInState(SprintStateType type) {
        this.ensureHasSprint();
        this.sprint.validateState(type);
    }

    private void ensureHasSprint() { // Complexity 2
        if (this.sprint == null) { // +1 (if statement)
            throw new IllegalStateException("Sprint is not set for this backlog item!");
        }
    }
}
