package com.avansdevops.pipeline.actions;

import com.avansdevops.AvansDevOps;

import java.util.List;

public class UtilityAction implements Action {
    private final List<String> commands;

    public UtilityAction(List<String> commands) {
        this.commands = commands;
    }

    @Override
    public boolean execute() {
        for (String command : this.commands) {
            AvansDevOps.LOGGER.info("Executing command: {}", command);
        }
        return true;
    }
}
