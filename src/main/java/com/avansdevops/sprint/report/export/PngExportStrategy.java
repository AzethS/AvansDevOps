package com.avansdevops.sprint.report.export;

/**
 * Strategy Pattern (Behavioral)
 */
public class PngExportStrategy implements ExportStrategy {
    @Override
    public void export(String contents) {
        System.out.printf("Exporting PNG:\n\n%s\n", contents);
    }
}
