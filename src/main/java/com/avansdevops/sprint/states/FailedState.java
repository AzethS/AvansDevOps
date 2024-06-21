package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.user.Role;

/**
 * State Pattern (Behavioral)
 * Observer pattern (Behavioral)
 */
public class FailedState extends SprintState {
    protected FailedState(Sprint context, SprintStateType stateType) {
        super(context, stateType);
    }

    @Override
    public void onStateChanged() {
        super.onStateChanged();
        this.context.notifySubscribers(
                "Sprint has failed",
                user -> user.getRole() == Role.PRODUCT_OWNER || user.getRole() == Role.SCRUM_MASTER
        );
    }
}
