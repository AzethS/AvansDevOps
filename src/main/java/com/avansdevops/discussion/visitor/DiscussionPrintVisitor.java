package com.avansdevops.discussion.visitor;

import com.avansdevops.discussion.Comment;
import com.avansdevops.discussion.Discussion;

/**
 * Visitor Pattern (Behavioral)
 */
public class DiscussionPrintVisitor implements DiscussionVisitor {
    @Override
    public void visit(Discussion discussion) {
        System.out.printf("Discussion: %s | %s\n", discussion.getItem().getTitle(), discussion.isEditable() ? "Open" : "Closed");
    }

    @Override
    public void visit(Comment comment) {
        if (comment.getParent() == null) {
            System.out.printf("Comment: %s\n", comment);
        } else {
            Comment parent = comment.getParent();
            System.out.printf("Reply to (%s): %s\n", parent, comment);
        }
    }
}
