package com.avansdevops.discussion;

import com.avansdevops.discussion.visitor.DiscussionVisitable;
import com.avansdevops.discussion.visitor.DiscussionVisitor;
import com.avansdevops.user.User;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern (Structural)
 * Visitor Pattern (Behavioral)
 */
public class Comment implements DiscussionVisitable {
    private final User author;
    private final String content;
    private final List<Comment> replies;

    private final Discussion discussion;
    @Nullable
    private final Comment parent;

    protected Comment(User author, String content, Discussion discussion, @Nullable Comment parent) {
        this.author = author;
        this.content = content;
        this.discussion = discussion;
        this.parent = parent;
        this.replies = new ArrayList<>();
    }

    @Override
    public void accept(DiscussionVisitor visitor) {
        visitor.visit(this);
        for (Comment reply : this.replies) {
            reply.accept(visitor);
        }
    }

    public Comment addReply(User author, String content) {
        if (!this.discussion.isEditable()) {
            throw new IllegalStateException("Discussion is not editable");
        }

        Comment comment = new Comment(author, content, this.discussion, this);
        this.replies.add(comment);
        return comment;
    }

    public User getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }

    public Discussion getDiscussion() {
        return this.discussion;
    }

    @Nullable
    public Comment getParent() {
        return this.parent;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.author.getName(), this.content);
    }
}
