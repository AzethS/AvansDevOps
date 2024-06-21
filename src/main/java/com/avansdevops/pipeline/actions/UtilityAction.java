package com.avansdevops.pipeline.actions;

import com.avansdevops.AvansDevOps;

import java.util.List;

public class UtilityAction implements Action {
    private final List<String> commands;

    public UtilityAction(List<String> commands) {
        this.commands = commands;
    }

    @Override
    public boolean execute() { // Complexity 2
        for (String command : this.commands) { // +1 (loop)
            AvansDevOps.LOGGER.info("Executing command: {}", command);
        }
        return true;
    }
}
