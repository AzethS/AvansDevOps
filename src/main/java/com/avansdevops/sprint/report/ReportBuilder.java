package com.avansdevops.sprint.report;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.export.ExportStrategy;
import com.avansdevops.sprint.report.export.PdfExportStrategy;
import com.avansdevops.sprint.report.types.ReportType;

/**
 * Builder Pattern (Creational)
 */
public class ReportBuilder {
    private final ReportType reportType;
    private final Sprint sprint;
    private String header;
    private String footer;
    private ExportStrategy exportStrategy;

    public ReportBuilder(ReportType reportType, Sprint sprint) {
        this.reportType = reportType;
        this.sprint = sprint;

        this.exportStrategy = new PdfExportStrategy();
        this.header = "";
        this.footer = "";
    }

    public ReportBuilder header(String header) {
        this.header = header;
        return this;
    }

    public ReportBuilder footer(String footer) {
        this.footer = footer;
        return this;
    }

    public ReportBuilder exportStrategy(ExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
        return this;
    }

    public Report build() {
        return this.reportType.create(this.sprint, this.header, this.footer, this.exportStrategy);
    }
}
