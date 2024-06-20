package com.avansdevops.project;

import com.avansdevops.discussion.Discussion;
import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.user.User;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final List<Sprint> sprints = new ArrayList<>();
    private final List<User> members = new ArrayList<>();
    private final ProductBacklog productBacklog = new ProductBacklog();
    private final Forum forum = new Forum();
    private final String name;
    private final Repository repo;

    public Project(String name, Repository repo) {
        this.name = name;
        this.repo = repo;
    }

    public void addSprint(Sprint sprint) {
        this.sprints.add(sprint);
    }

    public void addMember(User user) {
        this.members.add(user);
    }

    public void addBacklogItem(BacklogItem item) {
        this.productBacklog.addItem(item);
    }

    public void addDiscussion(Discussion discussion) {
        this.forum.addDiscussion(discussion);
    }

    public List<Sprint> getSprints() {
        return this.sprints;
    }

    public List<User> getMembers() {
        return this.members;
    }

    public ProductBacklog getProductBacklog() {
        return this.productBacklog;
    }

    public Forum getForum() {
        return this.forum;
    }

    public Repository getRepo() {
        return this.repo;
    }

    public String getName() {
        return this.name;
    }
}
