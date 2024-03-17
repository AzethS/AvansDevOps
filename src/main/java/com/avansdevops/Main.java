package com.avansdevops;

import com.avansdevops.discussion.Discussion;
import com.avansdevops.discussion.visitor.DiscussionPrintVisitor;
import com.avansdevops.discussion.visitor.DiscussionVisitor;
import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.sprint.report.Report;
import com.avansdevops.sprint.report.ReportBuilder;
import com.avansdevops.sprint.report.export.PdfExportStrategy;
import com.avansdevops.sprint.report.export.PngExportStrategy;
import com.avansdevops.sprint.report.types.ReportType;
import com.avansdevops.user.User;

public class Main {

    public static void main(String[] args) {
        // TODO: Temporary testing code. To be replaced with jUnit.
        separator("Report");
        testReport();

        separator("Discussion");
        testDiscussion();

        separator("Backlog States");
        testBacklogStates();

        separator("Finished");
    }

    private static void separator(String title) {
        System.out.printf("----------------------------------------------- %s -----------------------------------------------\n", title);
    }

    private static void testReport() {
        Report report = new ReportBuilder(ReportType.BURNDOWN, new Sprint())
                .exportStrategy(new PngExportStrategy())
                .header("Header")
                .footer("Footer")
                .build();

        report.export();
        report.setExportStrategy(new PdfExportStrategy());
        report.export();
    }

    private static void testDiscussion() {
        User user = new User("John");
        User user2 = new User("Jane");
        User user3 = new User("Jack");

        BacklogItem item = new BacklogItem(new Sprint(), "Exporting Strategy");
        Discussion discussion = new Discussion(item);

        discussion.addComment(discussion.commentBuilder()
                .author(user3)
                .content("This is a simple comment")
                .build()
        );

        discussion.addComment(discussion.commentBuilder()
                .author(user)
                .content("This is a comment with replies")
                .addReply(discussion.commentBuilder()
                        .author(user2)
                        .content("This is a reply")
                        .addReply(discussion.commentBuilder()
                                .author(user3)
                                .content("This is a reply to a reply")
                        )
                ).build()
        );

        DiscussionVisitor visitor = new DiscussionPrintVisitor();
        discussion.accept(visitor);
    }

    private static void testBacklogStates() {
        BacklogItem item = new BacklogItem(new Sprint(), "State Patterns");

        System.out.println("# Fail during testing phase (by tester)");
        item.getState().transferToDoing();
        item.getState().transferToReadyForTesting();
        item.getState().transferToTesting();
        item.getState().transferToTodo();

        System.out.println("\n# Fail during tested phase (by lead developer: not definition of done)");
        item.getState().transferToDoing();
        item.getState().transferToReadyForTesting();
        item.getState().transferToTesting();
        item.getState().transferToTested();
        item.getState().transferToReadyForTesting();

        System.out.println("\n# Success");
        item.getState().transferToTesting();
        item.getState().transferToTested();
        item.getState().transferToDone();
    }
}
