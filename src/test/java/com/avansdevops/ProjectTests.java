package com.avansdevops;

import com.avansdevops.discussion.Discussion;
import com.avansdevops.project.Commit;
import com.avansdevops.project.Project;
import com.avansdevops.project.Repository;
import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.sprint.backlog.states.BacklogItemStateType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectTests {

    @Test
    void repositoryPushWithoutCommitShouldFail() {
        Project project = new Project("AvansDevOps", new Repository("https://github.com/AzethS/AvansDevOps"));

        Assertions.assertThrows(IllegalStateException.class, () -> project.getRepo().push("a1b2c3"));
    }

    @Test
    void repositoryPushWithRemoteCommitShouldFail() {
        Project project = new Project("AvansDevOps", new Repository("https://github.com/AzethS/AvansDevOps"));

        Commit commit = new Commit("Initial commit", Map.of());
        project.getRepo().commit("a1b2c3", commit);
        project.getRepo().push("a1b2c3");

        Assertions.assertThrows(IllegalStateException.class, () -> project.getRepo().push("a1b2c3"));
    }

    @Test
    void repositoryBacklogItemsShouldBeSorted() {
        Project project = new Project("AvansDevOps", new Repository("https://github.com/AzethS/AvansDevOps"));
        Sprint sprint = new Sprint();

        BacklogItem item1 = new BacklogItem("Item 1");
        BacklogItem item2 = new BacklogItem("Item 2");
        BacklogItem item3 = new BacklogItem("Item 3");
        BacklogItem item4 = new BacklogItem("Item 4");

        item1.setSprint(sprint);
        item1.setState(BacklogItemStateType.DONE.create(item1));

        project.addBacklogItem(item3);
        project.addBacklogItem(item1);
        project.addBacklogItem(item4);
        project.addBacklogItem(item2);

        assertThat(project.getProductBacklog().getBacklogItems()).containsExactly(item2, item3, item4, item1);
    }

    @Test
    void repositoryForumDiscussionsShouldBeSorted() {
        Project project = new Project("AvansDevOps", new Repository("https://github.com/AzethS/AvansDevOps"));
        Sprint sprint = new Sprint();

        BacklogItem item1 = new BacklogItem("Item 1");
        BacklogItem item2 = new BacklogItem("Item 2");
        BacklogItem item3 = new BacklogItem("Item 3");
        BacklogItem item4 = new BacklogItem("Item 4");

        Discussion discussion1 = new Discussion(item1);
        Discussion discussion2 = new Discussion(item2);
        Discussion discussion3 = new Discussion(item3);
        Discussion discussion4 = new Discussion(item4);

        item1.setSprint(sprint);
        item1.setState(BacklogItemStateType.DONE.create(item1));

        project.addDiscussion(discussion3);
        project.addDiscussion(discussion1);
        project.addDiscussion(discussion4);
        project.addDiscussion(discussion2);

        assertThat(project.getForum().getDiscussions()).containsExactly(discussion2, discussion3, discussion4, discussion1);
    }
}
