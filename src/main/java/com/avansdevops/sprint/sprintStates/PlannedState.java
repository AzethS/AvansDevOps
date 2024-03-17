package com.avansdevops.sprint.sprintStates;

import com.avansdevops.sprint.Sprint;


public class PlannedState extends SprintState{

    public PlannedState(Sprint context) {
        super(context);
    }

    @Override
    public void transferToPlanned() {
        
        throw new UnsupportedOperationException("Already in Planned state'");
    }

    @Override
    public void transferToInProgress() {
        
        System.out.println("Transferring from Planned to InProgress");
        this.context.setState(new InProgressState(this.context));
    }

    @Override
    public void transferToInReview() {
        
        throw new UnsupportedOperationException("Cannot transfer from Planned to InReview");
    }

    @Override
    public void transferToFinished() {
        
        throw new UnsupportedOperationException("Cannot transfer from Todo to Finished");
    }
    
}
