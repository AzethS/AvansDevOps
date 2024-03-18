package com.avansdevops.pipeline.actions;

public class TestAction implements Action {
    private final String testingFramework;

    public TestAction(String testingFramework) {
        this.testingFramework = testingFramework;
    }

    @Override
    public boolean execute() {
        return this.runTests() && this.publishTestResults();
    }

    public boolean runTests() {
        System.out.printf("Testing with %s...%n", this.testingFramework);
        return true;
    }

    public boolean publishTestResults() {
        System.out.println("Publishing test results...");
        return true;
    }
}
