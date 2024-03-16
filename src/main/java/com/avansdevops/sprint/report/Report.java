package com.avansdevops.sprint.report;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.export.ExportStrategy;

public abstract class Report {
    private final String contents;
    private ExportStrategy exportStrategy;

    public Report(Sprint sprint, String header, String footer, ExportStrategy reportType) {
        this.exportStrategy = reportType;
        this.contents = this.generateReport(header, footer, sprint);
    }

    protected abstract String generate(Sprint sprint);

    private String generateReport(String header, String footer, Sprint sprint) {
        StringBuilder builder = new StringBuilder();
        if (!header.isEmpty()) {
            builder.append(header);
            builder.append("\n\n");
        }

        builder.append(this.generate(sprint));

        if (!footer.isEmpty()) {
            builder.append("\n\n");
            builder.append(footer);
        }

        return builder.toString();
    }

    public void export() {
        this.exportStrategy.export(this.contents);
    }

    public void setExportStrategy(ExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    public String getContents() {
        return this.contents;
    }
}
