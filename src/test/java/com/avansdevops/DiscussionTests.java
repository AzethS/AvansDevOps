package com.avansdevops;

import com.avansdevops.discussion.Comment;
import com.avansdevops.discussion.Discussion;
import com.avansdevops.discussion.visitor.DiscussionVisitor;
import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.sprint.backlog.states.BacklogItemStateType;
import com.avansdevops.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscussionTests {

    @Test
    void addCommentToFinishedBacklogItemShouldFail() {
        User user = new User("John");

        BacklogItem item = new BacklogItem(new Sprint(), "Finished Backlog Item");
        item.setState(BacklogItemStateType.DONE.create(item));

        Discussion discussion = new Discussion(item);
        Comment comment = discussion.comment()
                .author(user)
                .content("This is a simple comment")
                .build();

        Assertions.assertThrows(IllegalStateException.class, () -> discussion.addComment(comment));
    }

    @Test
    void addReplyToFinishedBacklogItemShouldFail() {
        User user = new User("John");
        User user2 = new User("Jane");

        BacklogItem item = new BacklogItem(new Sprint(), "Finished Backlog Item");
        item.setState(BacklogItemStateType.DONE.create(item));

        Discussion discussion = new Discussion(item);
        Comment comment = discussion.comment()
                .author(user)
                .content("This is a simple comment")
                .build();

        Assertions.assertThrows(IllegalStateException.class, () -> comment.addReply(user2, "This is a reply"));
    }

    @Test
    void commentRepliesShouldBeNestedCorrectly() {
        User user = new User("John");
        User user2 = new User("Jane");
        User user3 = new User("Jack");

        BacklogItem item = new BacklogItem(new Sprint(), "Todo Backlog Item");
        Discussion discussion = new Discussion(item);

        discussion.addComment(discussion.comment()
                .author(user3)
                .content("This is a simple comment")
                .build()
        );

        discussion.addComment(discussion.comment()
                .author(user)
                .content("This is a comment with replies")
                .addReply(discussion.comment()
                        .author(user2)
                        .content("This is a reply")
                        .addReply(discussion.comment()
                                .author(user3)
                                .content("This is a reply to a reply")
                        )
                ).build()
        );

        discussion.accept(new DiscussionVisitor() {
            @Override
            public void visit(Discussion discussion) {
            }

            @Override
            public void visit(Comment comment) {
                switch (comment.getContent()) {
                    case "This is a simple comment",
                         "This is a comment with replies" -> {
                        Assertions.assertNull(comment.getParent());
                    }
                    case "This is a reply" -> {
                        Assertions.assertNotNull(comment.getParent());
                        Assertions.assertEquals("This is a comment with replies", comment.getParent().getContent());
                    }
                    case "This is a reply to a reply" -> {
                        Assertions.assertNotNull(comment.getParent());
                        Assertions.assertEquals("This is a reply", comment.getParent().getContent());
                    }
                }
            }
        });
    }
}