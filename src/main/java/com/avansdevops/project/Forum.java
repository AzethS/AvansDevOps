package com.avansdevops.project;

import com.avansdevops.discussion.Discussion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Forum {
    private final List<Discussion> discussions = new ArrayList<>();

    public void addDiscussion(Discussion discussion) { // Complexity 2
        this.discussions.add(discussion);
        this.discussions.sort(Comparator.comparing((Discussion d) -> !d.isEditable()) // +1 (condition in lambda)
                .thenComparing(d -> d.getItem().getTitle())
        );
    }

    public List<Discussion> getDiscussions() {
        return this.discussions;
    }
}
