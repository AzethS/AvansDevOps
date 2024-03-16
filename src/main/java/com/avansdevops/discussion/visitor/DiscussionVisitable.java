package com.avansdevops.discussion.visitor;

/**
 * Visitor Pattern (Behavioral)
 */
public interface DiscussionVisitable {
    void accept(DiscussionVisitor visitor);
}
