package com.avansdevops;

import com.avansdevops.discussion.Discussion;
import com.avansdevops.discussion.visitor.DiscussionPrintVisitor;
import com.avansdevops.discussion.visitor.DiscussionVisitor;
import com.avansdevops.notifications.strategy.SlackNotificationStrategy;
import com.avansdevops.notifications.strategy.SmsNotificationStrategy;
import com.avansdevops.pipeline.Pipeline;
import com.avansdevops.pipeline.actions.*;
import com.avansdevops.sprint.ReleaseSprint;
import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.sprint.report.Report;
import com.avansdevops.sprint.report.ReportBuilder;
import com.avansdevops.sprint.report.export.PdfExportStrategy;
import com.avansdevops.sprint.report.export.PngExportStrategy;
import com.avansdevops.sprint.report.types.ReportType;
import com.avansdevops.sprint.states.SprintStateType;
import com.avansdevops.user.Role;
import com.avansdevops.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AvansDevOps {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        separator("Report");
        testReport();

        separator("Discussion");
        testDiscussion();

        separator("Backlog States");
        testBacklogStates();

        separator("Pipeline");
        testSprintWithPipeline();

        separator("Finished");
    }

    private static void separator(String title) {
        LOGGER.info("------------------------------------------------- {} -------------------------------------------------", title);
    }

    private static void testReport() {
        Report report = new ReportBuilder(new Sprint())
                .type(ReportType.BURNDOWN)
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

        Sprint sprint = new Sprint();
        BacklogItem item = new BacklogItem("Exporting Strategy");
        sprint.addBacklogItem(item);
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

        DiscussionVisitor visitor = new DiscussionPrintVisitor();
        discussion.accept(visitor);
    }

    private static void testBacklogStates() {
        Sprint sprint = createSprintWithPipeline();

        BacklogItem item = new BacklogItem("State Patterns");
        sprint.addBacklogItem(item);

        sprint.setState(SprintStateType.IN_PROGRESS.create(sprint));

        LOGGER.info("# Fail during testing phase (by tester)");
        item.getState().transferToDoing();
        item.getState().transferToReadyForTesting();
        item.getState().transferToTesting();
        item.getState().transferToTodo();

        LOGGER.info("");
        LOGGER.info("# Fail during tested phase (by lead developer: not definition of done)");
        item.getState().transferToDoing();
        item.getState().transferToReadyForTesting();
        item.getState().transferToTesting();
        item.getState().transferToTested();
        item.getState().transferToReadyForTesting();

        LOGGER.info("");
        LOGGER.info("# Success");
        item.getState().transferToTesting();
        item.getState().transferToTested();
        item.getState().transferToDone();
    }

    private static Sprint createSprintWithPipeline() {
        Pipeline pipeline = new Pipeline();
        pipeline.addAction(new SourcesAction("https://github.com/"));
        pipeline.addAction(new PackageAction(List.of(
                "com.google.code.gson:gson:2.10.1",
                "junit:junit:4.13.2"
        )));
        pipeline.addAction(new BuildAction("Maven"));
        pipeline.addAction(new TestAction("jUnit"));
        pipeline.addAction(new AnalyseAction("SonarQube"));
        pipeline.addAction(new DeployAction("Azure"));
        pipeline.addAction(new UtilityAction(List.of(
                "echo 'Hello, World!'"
        )));

        User productOwner = new User("Jack", Role.PRODUCT_OWNER);
        User scrumMaster = new User("Jill", Role.SCRUM_MASTER);
        User leadDeveloper = new User("John", Role.LEAD_DEVELOPER, new SlackNotificationStrategy());
        User tester = new User("Jane", Role.TESTER, new SmsNotificationStrategy());

        Sprint sprint = new ReleaseSprint(pipeline);
        sprint.addParticipant(productOwner);
        sprint.addParticipant(scrumMaster);
        sprint.addParticipant(leadDeveloper);
        sprint.addParticipant(tester);
        return sprint;
    }

    private static void testSprintWithPipeline() {
        Sprint failedSprint = createSprintWithPipeline();
        failedSprint.getState().transferToInProgress();
        failedSprint.getState().transferToInReview();
        failedSprint.getState().transferToFailed();
        LOGGER.info("");

        Sprint finishedSprint = createSprintWithPipeline();
        finishedSprint.getState().transferToInProgress();
        finishedSprint.getState().transferToInReview();
        finishedSprint.getState().transferToFinished();
    }
}
