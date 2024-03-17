package com.avansdevops.pipeline.actions;

public class DeployAction implements Action {
    private final String deploymentTarget;

    public DeployAction(String deploymentTarget) {
        this.deploymentTarget = deploymentTarget;
    }

    @Override
    public boolean execute() {
        System.out.printf("Deploying to %s...%n", this.deploymentTarget);
        return true;
    }
}
