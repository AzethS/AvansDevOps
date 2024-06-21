package com.avansdevops.sprint.report.types;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.Report;
import com.avansdevops.sprint.report.export.ExportStrategy;

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

    public Report create(Sprint sprint, String header, String footer, ExportStrategy exportStrategy) {
        return this.factory.create(sprint, header, footer, exportStrategy);
    }

    @FunctionalInterface
    public interface ReportFactory {
        Report create(Sprint sprint, String header, String footer, ExportStrategy reportType);
    }
}