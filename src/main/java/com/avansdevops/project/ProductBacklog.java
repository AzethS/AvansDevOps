package com.avansdevops.project;

import com.avansdevops.sprint.backlog.BacklogItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductBacklog {
    private final List<BacklogItem> items = new ArrayList<>();

    public void addItem(BacklogItem item) {
        this.items.add(item);
        this.items.sort(Comparator.comparing(BacklogItem::isDone)
                .thenComparing(BacklogItem::getTitle)
        );
    }

    public List<BacklogItem> getBacklogItems() {
        return this.items;
    }
}