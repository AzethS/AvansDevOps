package com.avansdevops.project;

import java.util.Map;

public class Commit {
    private final String title;
    private final Map<Integer, String> changes;
    private boolean isOnRemote;

    public Commit(String title, Map<Integer, String> changes) {
        this.title = title;
        this.changes = changes;
    }

    public String getTitle() {
        return this.title;
    }

    public Map<Integer, String> getChanges() {
        return this.changes;
    }

    public boolean isOnRemote() {
        return this.isOnRemote;
    }

    public void setRemote(boolean isOnRemote) {
        this.isOnRemote = isOnRemote;
    }
}
