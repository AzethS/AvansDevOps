package com.avansdevops.pipeline.actions;

import java.util.List;

public class PackageAction implements Action {
    private final List<String> packages;

    public PackageAction(List<String> packages) {
        this.packages = packages;
    }

    @Override
    public boolean execute() {
        for (String packageId : this.packages) {
            System.out.printf("Installing package %s...%n", packageId);

            boolean success = true;
            if (!success) { // Simulate success, cancel pipeline if installation of package fails.
                return false;
            }
        }

        return true;
    }
}
