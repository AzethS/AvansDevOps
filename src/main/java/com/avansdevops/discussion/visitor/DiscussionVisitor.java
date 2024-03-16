package com.avansdevops.discussion.visitor;

import com.avansdevops.discussion.Comment;
import com.avansdevops.discussion.Discussion;

/**
 * Visitor Pattern (Behavioral)
 */
public interface DiscussionVisitor {
    void visit(Discussion discussion);

    void visit(Comment comment);
}
