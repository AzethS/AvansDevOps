package com.avansdevops.sprint;


import java.util.List;

import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.sprint.sprintStates.PlannedState;
import com.avansdevops.sprint.sprintStates.SprintState;
import com.avansdevops.user.User;

public class Sprint {
    private SprintState  state = new PlannedState(this);
    private List<User> users;
    private User scrumMaster;
    private User productOwner;
    private List<BacklogItem> backlog;

    public Sprint(List<User> users, User scrumMaster, User productOwner, List<BacklogItem> backlog) {
        this.users = users;
        this.scrumMaster = scrumMaster;
        this.productOwner = productOwner;
        this.backlog = backlog;
    }

    public void setState(SprintState state) {
        this.state = state;
    }

    public SprintState getState() {
        return this.state;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getScrumMaster() {
        return scrumMaster;
    }

    public void setScrumMaster(User scrumMaster) {
        this.scrumMaster = scrumMaster;
    }

    public User getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(User productOwner) {
        this.productOwner = productOwner;
    }

    public List<BacklogItem> getBacklog() {
        return backlog;
    }

    public void setBacklog(List<BacklogItem> backlog) {
        this.backlog = backlog;
    }

}
