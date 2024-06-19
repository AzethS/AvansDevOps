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

    public Discussion(BacklogItem item) {
        this(item, new ArrayList<>());
    }

    public Discussion(BacklogItem item, List<Comment> comments) {
        this.item = item;
        this.comments = comments;
    }

    public BacklogItem getItem() {
        return this.item;
    }

    public CommentBuilder comment() {
        return new CommentBuilder(this);
    }

    public void addComment(Comment comment) {
        if (!this.isEditable()) {
            throw new IllegalStateException("Discussion is not editable");
        }
        this.comments.add(comment);
    }

    public boolean isEditable() {
        return !this.item.isDone();
    }

    @Override
    public void accept(DiscussionVisitor visitor) {
        visitor.visit(this);
        for (Comment comment : this.comments) {
            comment.accept(visitor);
        }
    }
}
