package com.avansdevops.sprint.report;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.export.ExportStrategy;

/**
 * Factory Pattern (Creational)
 */
public interface ReportFactory {
    Report create(Sprint sprint, String header, String footer, ExportStrategy reportType);
}