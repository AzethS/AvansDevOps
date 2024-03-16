package com.avansdevops;

import com.avansdevops.discussion.Comment;
import com.avansdevops.discussion.Discussion;
import com.avansdevops.discussion.visitor.DiscussionVisitor;
import com.avansdevops.discussion.visitor.DiscussionPrintVisitor;
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

        Discussion discussion = new Discussion("Exporting Strategy", new BacklogItem());
        Comment comment = new Comment(user, "This is a comment");
        discussion.addComment(comment);

        Comment reply1 = comment.addReply(user2, "This is a reply");
        Comment reply2 = comment.addReply(user3, "This is another reply");
        Comment reply3 = reply2.addReply(user, "This is a reply to a reply");

        DiscussionVisitor visitor = new DiscussionPrintVisitor();
        discussion.accept(visitor);
    }
}
