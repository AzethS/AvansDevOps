package com.avansdevops.pipeline.actions;

public class BuildAction implements Action {
    private final String buildSystem;

    public BuildAction(String buildSystem) {
        this.buildSystem = buildSystem;
    }

    @Override
    public boolean execute() {
        System.out.printf("Building with %s...%n", this.buildSystem);
        return true;
    }
}
