package com.avansdevops.pipeline;

import com.avansdevops.pipeline.actions.Action;

import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    private final List<Action> actions = new ArrayList<>();

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public boolean run() { // Complexity 3
        for (Action action : this.actions) { // +1 (loop)
            if (!action.execute()) { // +1 (if statement)
                return false;
            }
        }
        return true;
    }
}
