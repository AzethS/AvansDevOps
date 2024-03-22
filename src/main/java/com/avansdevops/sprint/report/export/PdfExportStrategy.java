package com.avansdevops.sprint.report.export;

import com.avansdevops.AvansDevOps;

/**
 * Strategy Pattern (Behavioral)
 */
public class PdfExportStrategy implements ExportStrategy {
    @Override
    public void export(String contents) {
        AvansDevOps.LOGGER.info("Exporting PDF:\n\n{}", contents);
    }
}
