package com.avansdevops.pipeline.actions;

import com.avansdevops.AvansDevOps;

public class DeployAction implements Action {
    private final String deploymentTarget;

    public DeployAction(String deploymentTarget) {
        this.deploymentTarget = deploymentTarget;
    }

    @Override
    public boolean execute() {
        AvansDevOps.LOGGER.info("Deploying to {}...", this.deploymentTarget);
        return true;
    }
}
