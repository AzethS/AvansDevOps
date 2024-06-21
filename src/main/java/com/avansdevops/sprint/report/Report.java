package com.avansdevops.sprint.report;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.export.ExportStrategy;

/**
 * Strategy Pattern (Behavioral)
 */
public abstract class Report {
    private final String contents;
    private ExportStrategy exportStrategy;

    protected Report(Sprint sprint, String header, String footer, ExportStrategy reportType) {
        this.exportStrategy = reportType;
        this.contents = this.generateReport(header, footer, sprint);
    }

    public abstract String generate(Sprint sprint);

    private String generateReport(String header, String footer, Sprint sprint) { // Complexity 3
        StringBuilder builder = new StringBuilder();
        if (!header.isEmpty()) { // +1 (if statement)
            builder.append(header);
            builder.append("\n\n");
        }

        builder.append(this.generate(sprint));

        if (!footer.isEmpty()) { // +1 (if statement)
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

    public ExportStrategy getExportStrategy() {
        return this.exportStrategy;
    }

    public String getContents() {
        return this.contents;
    }
}
