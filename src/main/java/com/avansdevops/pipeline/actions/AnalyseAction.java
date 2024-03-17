package com.avansdevops.pipeline.actions;

public class AnalyseAction implements Action {
    private final String analysisTool;

    public AnalyseAction(String analysisTool) {
        this.analysisTool = analysisTool;
    }

    @Override
    public boolean execute() {
        System.out.printf("Performing code analysis with %s...%n", this.analysisTool);
        return true;
    }
}
