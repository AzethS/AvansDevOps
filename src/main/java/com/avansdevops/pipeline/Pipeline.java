package com.avansdevops.pipeline;

import com.avansdevops.pipeline.actions.Action;

import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    private final List<Action> actions = new ArrayList<>();

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public boolean execute() {
        System.out.println("Executing pipeline...");
        for (Action action : this.actions) {
            if (!action.execute()) {
                System.out.println("Pipeline failed!");
                return false;
            }
        }
        System.out.println("Pipeline succeeded!");
        return true;
    }
}
