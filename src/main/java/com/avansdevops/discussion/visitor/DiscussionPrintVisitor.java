package com.avansdevops.discussion.visitor;

import com.avansdevops.AvansDevOps;
import com.avansdevops.discussion.Comment;
import com.avansdevops.discussion.Discussion;

/**
 * Visitor Pattern (Behavioral)
 */
public class DiscussionPrintVisitor implements DiscussionVisitor {
    @Override
    public void visit(Discussion discussion) {
        AvansDevOps.LOGGER.info("Discussion: {} | {}", discussion.getItem().getTitle(), discussion.isEditable() ? "Open" : "Closed");
    }

    @Override
    public void visit(Comment comment) {
        if (comment.getParent() == null) {
            AvansDevOps.LOGGER.info("Comment: {}", comment);
        } else {
            Comment parent = comment.getParent();
            AvansDevOps.LOGGER.info("Reply to ({}): {}", parent, comment);
        }
    }
}
