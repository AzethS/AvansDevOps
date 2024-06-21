package com.avansdevops.discussion.visitor;

import com.avansdevops.AvansDevOps;
import com.avansdevops.discussion.Comment;
import com.avansdevops.discussion.Discussion;

/**
 * Visitor Pattern (Behavioral)
 */
public class DiscussionPrintVisitor implements DiscussionVisitor {
    @Override
    public void visit(Discussion discussion) { // Complexity 2
        AvansDevOps.LOGGER.info(
                "Discussion: {} | {}",
                discussion.getItem().getTitle(),
                discussion.isEditable() ? "Open" : "Closed" // +1 (ternary operator)
        );
    }

    @Override
    public void visit(Comment comment) { // Complexity 2
        if (comment.getParent() == null) {  // +1 (if statement)
            AvansDevOps.LOGGER.info("Comment: {}", comment);
        } else {
            Comment parent = comment.getParent();
            AvansDevOps.LOGGER.info("Reply to ({}): {}", parent, comment);
        }
    }
}
