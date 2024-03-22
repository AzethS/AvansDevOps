package com.avansdevops.sprint.report.export;

import com.avansdevops.AvansDevOps;

/**
 * Strategy Pattern (Behavioral)
 */
public class PngExportStrategy implements ExportStrategy {
    @Override
    public void export(String contents) {
        AvansDevOps.LOGGER.info("Exporting PNG:\n\n{}", contents);
    }
}
