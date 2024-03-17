package com.avansdevops.sprint;


import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.sprint.states.PlannedState;
import com.avansdevops.sprint.states.SprintState;
import com.avansdevops.user.Role;
import com.avansdevops.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Sprint {
    private SprintState state = new PlannedState(this);
    private final List<User> users = new ArrayList<>();
    private final List<BacklogItem> backlog = new ArrayList<>();

    public void setState(SprintState state) {
        this.state = state;
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

    public void addUser(User user) {
        Role role = user.getRole();
        boolean canAdd = switch (role) {
            case PRODUCT_OWNER, SCRUM_MASTER -> !this.hasRole(role);
            default -> true;
        };

        if (canAdd) {
            this.users.add(user);
        }
    }

    private boolean hasRole(Role role) {
        for (User user : this.users) {
            if (user.getRole() == role) {
                return true;
            }
        }
        return false;
    }

    public void notifyObservers(String message, Predicate<Role> predicate) {
        for (User user : this.users) {
            if (predicate.test(user.getRole())) {
                // TODO
            }
        }
    }
}
