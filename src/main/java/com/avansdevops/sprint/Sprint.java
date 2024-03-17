package com.avansdevops.sprint;


import com.avansdevops.sprint.sprintStates.PlannedState;
import com.avansdevops.sprint.sprintStates.SprintState;

public class Sprint {
    private SprintState  state = new PlannedState(this);

    public void setState(SprintState state) {
        this.state = state;
    }

    public SprintState getState() {
        return this.state;
    }

}
