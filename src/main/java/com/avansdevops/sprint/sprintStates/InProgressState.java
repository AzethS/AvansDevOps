package com.avansdevops.sprint.sprintStates;

import com.avansdevops.sprint.Sprint;

public class InProgressState extends SprintState{

    protected InProgressState(Sprint context) {
        super(context);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void transferToPlanned() {
        
        throw new UnsupportedOperationException("Cannot transfer from InProgress to Planned");
    }

    @Override
    public void transferToInProgress() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferToInProgress'");
    }

    @Override
    public void transferToInReview() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferToInReview'");
    }

    @Override
    public void transferToFinished() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferToFinished'");
    }
    
}
