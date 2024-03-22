package com.avansdevops.pipeline.actions;

import com.avansdevops.AvansDevOps;

public class SourcesAction implements Action {
    private final String fetchUrl;

    public SourcesAction(String fetchUrl) {
        this.fetchUrl = fetchUrl;
    }

    @Override
    public boolean execute() {
        AvansDevOps.LOGGER.info("Fetching sources from {}...", this.fetchUrl);
        return true;
    }
}
