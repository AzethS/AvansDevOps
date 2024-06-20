package com.avansdevops.sprint.report.types;

import com.avansdevops.sprint.report.ReportFactory;

/**
 * Factory Pattern (Creational)
 */
public enum ReportType {
    BURNDOWN(BurndownReport::new),
    EFFORT_POINTS(EffortPointsReport::new),
    TEAM(TeamReport::new);

    private final ReportFactory factory;

    ReportType(ReportFactory factory) {
        this.factory = factory;
    }

    public ReportFactory factory() {
        return this.factory;
    }
}