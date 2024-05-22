package com.avansdevops.sprint.states;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.user.Role;

public class FailedState extends AbstractSprintState {
    protected FailedState(Sprint context, SprintState state) {
        super(context, state);
    }

    @Override
    public void onStateChange() {
        super.onStateChange();
        this.context.notifySubscribers(
                "Sprint has failed",
                user -> user.getRole() == Role.PRODUCT_OWNER || user.getRole() == Role.SCRUM_MASTER
        );
    }
}
