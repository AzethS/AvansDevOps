package com.avansdevops.pipeline.actions;

import java.util.List;

public class UtilityAction implements Action {
    private final List<String> commands;

    public UtilityAction(List<String> commands) {
        this.commands = commands;
    }

    @Override
    public boolean execute() {
        for (String command : this.commands) {
            System.out.println("Executing command: " + command);
        }
        return true;
    }
}
