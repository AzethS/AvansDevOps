package com.avansdevops.sprint.backlog;

import com.avansdevops.user.User;

public class Activity {
    private final String title;
    private User assignedUser;
    private boolean isFinished;

    public Activity(String title) {
        this.title = title;
    }

    public void setAssignedUser(User user) { // Complexity 2
        if (!user.getRole().isDeveloper()) { // +1 (if statement)
            throw new IllegalArgumentException("User is not a developer!");
        }

        this.assignedUser = user;
    }

    public void finish() {
        this.isFinished = true;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public User getAssignedUser() {
        return this.assignedUser;
    }

    public String getTitle() {
        return this.title;
    }
}
