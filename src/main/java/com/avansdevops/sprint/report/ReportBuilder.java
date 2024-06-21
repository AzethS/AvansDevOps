package com.avansdevops.sprint.report;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.export.ExportStrategy;
import com.avansdevops.sprint.report.types.ReportType;

/**
 * Builder Pattern (Creational)
 */
public class ReportBuilder {
    private final Sprint sprint;
    private ReportType reportType;
    private ExportStrategy exportStrategy;
    private String header;
    private String footer;

    public ReportBuilder(Sprint sprint) {
        this.sprint = sprint;
        this.header = "";
        this.footer = "";
    }

    public ReportBuilder type(ReportType reportType) {
        this.reportType = reportType;
        return this;
    }

    public ReportBuilder exportStrategy(ExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
        return this;
    }

    public ReportBuilder header(String header) {
        this.header = header;
        return this;
    }

    public ReportBuilder footer(String footer) {
        this.footer = footer;
        return this;
    }

    public Report build() { // Complexity 3
        if (this.reportType == null) { // +1 (if statement)
            throw new IllegalStateException("Report type is required");
        }

        if (this.exportStrategy == null) { // +1 (if statement)
            throw new IllegalStateException("Export strategy is required");
        }

        return this.reportType.create(this.sprint, this.header, this.footer, this.exportStrategy);
    }
}
