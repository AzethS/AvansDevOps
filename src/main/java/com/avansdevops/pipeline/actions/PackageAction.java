package com.avansdevops.pipeline.actions;

import com.avansdevops.AvansDevOps;

import java.util.List;

public class PackageAction implements Action {
    private final List<String> packages;

    public PackageAction(List<String> packages) {
        this.packages = packages;
    }

    @Override
    public boolean execute() { // Complexity 3
        for (String packageId : this.packages) { // +1 (loop)
            if (!this.installPackage(packageId)) { // +1 (if statement)
                return false;
            }
        }

        return true;
    }

    public boolean installPackage(String packageId) {
        AvansDevOps.LOGGER.info("Installing package {}...", packageId);
        return true;
    }
}
