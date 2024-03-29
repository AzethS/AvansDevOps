package com.avansdevops.sprint;


import com.avansdevops.notifications.observer.Publisher;
import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.sprint.states.PlannedState;
import com.avansdevops.sprint.states.SprintState;
import com.avansdevops.user.Role;
import com.avansdevops.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sprint extends Publisher<User> {
    private SprintState state = new PlannedState(this);
    private final Set<User> participants = new HashSet<>();
    private final List<BacklogItem> backlog = new ArrayList<>();

    public void setState(SprintState state) {
        this.state = state;
        state.onStateChange();
    }

    public SprintState getState() {
        return this.state;
    }

    public void addBacklogItem(BacklogItem item) {
        this.backlog.add(item);
    }

    public void removeBacklogItem(BacklogItem item) {
        this.backlog.remove(item);
    }

    public List<BacklogItem> getBacklog() {
        return this.backlog;
    }

    public void addParticipant(User user) {
        if (this.participants.contains(user)) {
            throw new IllegalArgumentException("User is already a participant of this sprint");
        }

        Role role = user.getRole();
        boolean canAdd = switch (role) {
            case PRODUCT_OWNER, SCRUM_MASTER -> !this.hasRole(role);
            default -> true;
        };

        if (canAdd) {
            this.participants.add(user);
            this.subscribe(user);
        } else {
            throw new IllegalArgumentException("Cannot add more than one participant with role " + role);
        }
    }

    public void removeParticipant(User user) {
        if (this.participants.remove(user)) {
            this.unsubscribe(user);
        }
    }

    private boolean hasRole(Role role) {
        for (User user : this.participants) {
            if (user.getRole() == role) {
                return true;
            }
        }
        return false;
    }

    public void onFinished() {
        // NO-OP
    }
}
