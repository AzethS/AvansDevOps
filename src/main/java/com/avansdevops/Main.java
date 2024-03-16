package com.avansdevops;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.Report;
import com.avansdevops.sprint.report.ReportBuilder;
import com.avansdevops.sprint.report.export.PdfExportStrategy;
import com.avansdevops.sprint.report.export.PngExportStrategy;
import com.avansdevops.sprint.report.types.ReportType;

public class Main {

    public static void main(String[] args) {
        Report report = new ReportBuilder(ReportType.BURNDOWN, new Sprint())
                .exportStrategy(new PngExportStrategy())
                .header("Header")
                .footer("Footer")
                .build();

        report.export();
        report.setExportStrategy(new PdfExportStrategy());
        report.export();
    }
}
