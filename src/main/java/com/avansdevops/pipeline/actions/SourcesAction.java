package com.avansdevops.pipeline.actions;

public class SourcesAction implements Action {
    private final String fetchUrl;

    public SourcesAction(String fetchUrl) {
        this.fetchUrl = fetchUrl;
    }

    @Override
    public boolean execute() {
        System.out.printf("Fetching sources from %s...%n", this.fetchUrl);
        return true;
    }
}
