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
    @Nullable
    private final Comment parent;

    public Comment(User user, String content) {
        this(user, content, null, new ArrayList<>());
    }

    public Comment(User user, String content, List<Comment> replies) {
        this(user, content, null, replies);
    }

    public Comment(User user, String content, @Nullable Comment parent) {
        this(user, content, parent, new ArrayList<>());
    }

    public Comment(User author, String content, @Nullable Comment parent, List<Comment> replies) {
        this.author = author;
        this.content = content;
        this.replies = replies;
        this.parent = parent;
    }

    @Override
    public void accept(DiscussionVisitor visitor) {
        visitor.visit(this);
        for (Comment reply : this.replies) {
            reply.accept(visitor);
        }
    }

    public Comment addReply(User author, String content) {
        Comment comment = new Comment(author, content, this);
        this.replies.add(comment);
        return comment;
    }

    public User getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
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
