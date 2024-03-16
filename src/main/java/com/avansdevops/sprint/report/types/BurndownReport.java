package com.avansdevops.sprint.report.types;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.Report;
import com.avansdevops.sprint.report.export.ExportStrategy;

public class BurndownReport extends Report {
    public BurndownReport(Sprint sprint, String header, String footer, ExportStrategy reportType) {
        super(sprint, header, footer, reportType);
    }

    @Override
    public String generate(Sprint sprint) {
        return "Burndown Report";
    }
}
