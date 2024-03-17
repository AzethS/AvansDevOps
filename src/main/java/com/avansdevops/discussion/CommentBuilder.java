package com.avansdevops.discussion;

import com.avansdevops.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder Pattern (Creational)
 */
public class CommentBuilder {
    private final List<CommentBuilder> replies = new ArrayList<>();
    private final Discussion discussion;
    private User author;
    private String content;

    protected CommentBuilder(Discussion discussion) {
        this.discussion = discussion;
    }

    public CommentBuilder author(User author) {
        this.author = author;
        return this;
    }

    public CommentBuilder content(String content) {
        this.content = content;
        return this;
    }

    public CommentBuilder addReply(CommentBuilder reply) {
        this.replies.add(reply);
        return this;
    }

    public Comment build() {
        if (this.author == null) {
            throw new IllegalStateException("Author is required");
        }

        if (this.content == null) {
            throw new IllegalStateException("Content is required");
        }

        Comment comment = new Comment(this.author, this.content, this.discussion, null);
        this.recursiveAddReplies(comment, this.replies);
        return comment;
    }

    private void recursiveAddReplies(Comment comment, List<CommentBuilder> replies) {
        for (CommentBuilder reply : replies) {
            this.recursiveAddReplies(
                    comment.addReply(reply.author, reply.content),
                    reply.replies
            );
        }
    }
}
