package com.avansdevops.sprint.report.export;

/**
 * Strategy Pattern (Behavioral)
 */
public class PdfExportStrategy implements ExportStrategy {
    @Override
    public void export(String contents) {
        System.out.printf("Exporting PDF:\n\n%s\n", contents);
    }
}
