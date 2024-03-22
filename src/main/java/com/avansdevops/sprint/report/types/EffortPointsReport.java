package com.avansdevops.sprint.report.types;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.Report;
import com.avansdevops.sprint.report.export.ExportStrategy;

public class EffortPointsReport extends Report {
    public EffortPointsReport(Sprint sprint, String header, String footer, ExportStrategy reportType) {
        super(sprint, header, footer, reportType);
    }

    @Override
    public String generate(Sprint sprint) {
        return "Effort Points Report";
    }
}
