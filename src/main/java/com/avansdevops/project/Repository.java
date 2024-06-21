package com.avansdevops.project;

import com.avansdevops.AvansDevOps;

import java.util.LinkedHashMap;
import java.util.Map;

public class Repository {
    private final String repositoryUrl;
    private final Map<String, Commit> commits = new LinkedHashMap<>();

    public Repository(String url) {
        this.repositoryUrl = url;
    }

    public void commit(String hash, Commit commit) {
        this.commits.put(hash, commit);
    }

    public void push(String hash) { // Complexity 3
        Commit commit = this.commits.get(hash);
        if (commit == null) { // +1 (if statement)
            throw new IllegalStateException("No changes to push!");
        }

        if (commit.isOnRemote()) { // +1 (if statement)
            throw new IllegalStateException("Commit is already on the remote repository!");
        }

        // Integration: Push changes to the repository
        AvansDevOps.LOGGER.info("[{}] Commit '{}' was pushed to the repository", this.repositoryUrl, commit.getTitle());
        commit.setRemote(true);
    }

    public void pull() {
        Map<String, Commit> remoteCommits = this.pullInternal();
        this.commits.putAll(remoteCommits);
    }

    private Map<String, Commit> pullInternal() {
        // Integration: Pull changes from the repository
        return Map.of();
    }
}
