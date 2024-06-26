package com.avansdevops.sprint;


import com.avansdevops.notifications.observer.Publisher;
import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.sprint.states.SprintState;
import com.avansdevops.sprint.states.SprintStateType;
import com.avansdevops.states.StateContext;
import com.avansdevops.user.Role;
import com.avansdevops.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * State Pattern (Behavioral)
 * Observer pattern (Behavioral)
 */
public class Sprint extends Publisher<User> implements StateContext<SprintState> {
    private SprintState state = SprintStateType.PLANNED.create(this);
    private final Set<User> participants = new HashSet<>();
    private final List<BacklogItem> backlog = new ArrayList<>();

    @Override
    public SprintState getState() {
        return this.state;
    }

    @Override
    public void setState(SprintState state) {
        this.state = state;
    }

    public void addBacklogItem(BacklogItem item) {
        this.validateState(SprintStateType.PLANNED);
        this.backlog.add(item);
        item.setSprint(this);
    }

    public void removeBacklogItem(BacklogItem item) {
        this.validateState(SprintStateType.PLANNED);
        this.backlog.remove(item);
        item.setSprint(null);
    }

    public List<BacklogItem> getBacklog() {
        return this.backlog;
    }

    public void addParticipant(User user) { // Complexity 5
        if (this.participants.contains(user)) { // +1 (if statement)
            throw new IllegalArgumentException("User is already a participant of this sprint");
        }

        Role role = user.getRole();
        boolean canAdd = switch (role) {
            case PRODUCT_OWNER, SCRUM_MASTER -> !this.hasRole(role); // +1 (switch statement)
            default -> true; // +1 (switch statement)
        };

        if (canAdd) { // +1 (if statement)
            this.participants.add(user);
            this.subscribe(user);
        } else {
            throw new IllegalArgumentException("Cannot add more than one participant with role " + role);
        }
    }

    public void removeParticipant(User user) { // Complexity 2
        if (this.participants.remove(user)) { // +1 (if statement)
            this.unsubscribe(user);
        }
    }

    private boolean hasRole(Role role) { // Complexity 3
        for (User user : this.participants) { // +1 (loop)
            if (user.getRole() == role) { // +1 (if statement)
                return true;
            }
        }
        return false;
    }

    public void onFinished() {
        // NO-OP
    }

    public void validateState(SprintStateType type) { // Complexity 2
        if (this.state.getType() != type) { // +1 (if statement)
            throw new IllegalStateException("Sprint is not in the %s phase!".formatted(type));
        }
    }
}
