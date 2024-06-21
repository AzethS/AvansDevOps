package com.avansdevops.pipeline.actions;

import com.avansdevops.AvansDevOps;

public class TestAction implements Action {
    private final String testingFramework;

    public TestAction(String testingFramework) {
        this.testingFramework = testingFramework;
    }

    @Override
    public boolean execute() { // Complexity 3
        // +2 (2 conditions)
        return this.runTests() && this.publishTestResults();
    }

    public boolean runTests() {
        AvansDevOps.LOGGER.info("Running tests with {}...", this.testingFramework);
        return true;
    }

    public boolean publishTestResults() {
        AvansDevOps.LOGGER.info("Publishing test results...");
        return true;
    }
}
