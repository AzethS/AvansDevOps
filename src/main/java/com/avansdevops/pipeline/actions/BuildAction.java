package com.avansdevops.pipeline.actions;

import com.avansdevops.AvansDevOps;

public class BuildAction implements Action {
    private final String buildSystem;

    public BuildAction(String buildSystem) {
        this.buildSystem = buildSystem;
    }

    @Override
    public boolean execute() {
        AvansDevOps.LOGGER.info("Building with {}...", this.buildSystem);
        return true;
    }
}
