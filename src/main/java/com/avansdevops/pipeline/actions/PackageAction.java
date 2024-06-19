package com.avansdevops.pipeline.actions;

import com.avansdevops.AvansDevOps;

import java.util.List;

public class PackageAction implements Action {
    private final List<String> packages;

    public PackageAction(List<String> packages) {
        this.packages = packages;
    }

    @Override
    public boolean execute() {
        for (String packageId : this.packages) {
            if (!this.installPackage(packageId)) {
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
