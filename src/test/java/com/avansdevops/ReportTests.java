package com.avansdevops;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.Report;
import com.avansdevops.sprint.report.ReportBuilder;
import com.avansdevops.sprint.report.export.PdfExportStrategy;
import com.avansdevops.sprint.report.types.ReportType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ReportTests {

    @ParameterizedTest
    @CsvSource(value = {
            "BURNDOWN, com.avansdevops.sprint.report.types.BurndownReport",
            "EFFORT_POINTS, com.avansdevops.sprint.report.types.EffortPointsReport"
    })
    void reportShouldBeInstanceOfTheRightClass(ReportType type, Class<?> expected) {
        Report report = new ReportBuilder(new Sprint())
                .exportStrategy(new PdfExportStrategy())
                .type(type)
                .build();

        Assertions.assertInstanceOf(expected, report);
    }
}
