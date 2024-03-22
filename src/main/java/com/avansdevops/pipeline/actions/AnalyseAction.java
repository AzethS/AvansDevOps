package com.avansdevops.pipeline.actions;

import com.avansdevops.AvansDevOps;

public class AnalyseAction implements Action {
    private final String analysisTool;

    public AnalyseAction(String analysisTool) {
        this.analysisTool = analysisTool;
    }

    @Override
    public boolean execute() {
        AvansDevOps.LOGGER.info("Performing code analysis with {}...", this.analysisTool);
        return true;
    }
}
