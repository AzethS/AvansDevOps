package com.avansdevops.discussion;

import com.avansdevops.discussion.visitor.DiscussionVisitable;
import com.avansdevops.discussion.visitor.DiscussionVisitor;
import com.avansdevops.sprint.backlog.BacklogItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern (Structural)
 * Visitor Pattern (Behavioral)
 */
public class Discussion implements DiscussionVisitable {
    private final List<Comment> comments;
    private final BacklogItem item;
    private String title;

    public Discussion(String title, BacklogItem item) {
        this(title, item, new ArrayList<>());
    }

    public Discussion(String title, BacklogItem item, List<Comment> comments) {
        this.title = title;
        this.item = item;
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isClosed() {
        return false; // TODO: Check if backlog item resolved.
    }

    @Override
    public void accept(DiscussionVisitor visitor) {
        visitor.visit(this);
        for (Comment comment : this.comments) {
            comment.accept(visitor);
        }
    }
}
