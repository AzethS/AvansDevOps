package com.avansdevops;

import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.report.Report;
import com.avansdevops.sprint.report.ReportBuilder;
import com.avansdevops.sprint.report.export.ExportStrategy;
import com.avansdevops.sprint.report.export.PdfExportStrategy;
import com.avansdevops.sprint.report.types.ReportType;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

class ReportTests {
    private static LogCaptor logCaptor;

    @BeforeAll
    public static void setupLogCaptor() {
        logCaptor = LogCaptor.forClass(AvansDevOps.class);
        logCaptor.disableConsoleOutput();
    }

    @AfterEach
    public void clearLogs() {
        logCaptor.clearLogs();
    }

    @AfterAll
    public static void tearDown() {
        logCaptor.close();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "BURNDOWN, com.avansdevops.sprint.report.types.BurndownReport",
            "EFFORT_POINTS, com.avansdevops.sprint.report.types.EffortPointsReport",
            "TEAM, com.avansdevops.sprint.report.types.TeamReport"
    })
    void reportShouldBeInstanceOfTheRightClass(ReportType type, Class<?> expected) {
        Report report = new ReportBuilder(new Sprint())
                .exportStrategy(new PdfExportStrategy())
                .type(type)
                .build();

        Assertions.assertInstanceOf(expected, report);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "com.avansdevops.sprint.report.export.PdfExportStrategy",
            "com.avansdevops.sprint.report.export.PngExportStrategy"
    })
    void exportStrategyShouldBeInstanceOfTheRightClass(Class<ExportStrategy> strategy) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Report report = new ReportBuilder(new Sprint())
                .exportStrategy(strategy.getDeclaredConstructor().newInstance())
                .type(ReportType.BURNDOWN)
                .build();

        Assertions.assertInstanceOf(strategy, report.getExportStrategy());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "BURNDOWN, com.avansdevops.sprint.report.export.PdfExportStrategy, 'Exporting PDF:\n\nHeader\n\nBurndown Report\n\nFooter'",
            "BURNDOWN, com.avansdevops.sprint.report.export.PngExportStrategy, 'Exporting PNG:\n\nHeader\n\nBurndown Report\n\nFooter'",
            "EFFORT_POINTS, com.avansdevops.sprint.report.export.PdfExportStrategy, 'Exporting PDF:\n\nHeader\n\nEffort Points Report\n\nFooter'",
            "EFFORT_POINTS, com.avansdevops.sprint.report.export.PngExportStrategy, 'Exporting PNG:\n\nHeader\n\nEffort Points Report\n\nFooter'",
            "TEAM, com.avansdevops.sprint.report.export.PdfExportStrategy, 'Exporting PDF:\n\nHeader\n\nTeam Report\n\nFooter'",
            "TEAM, com.avansdevops.sprint.report.export.PngExportStrategy, 'Exporting PNG:\n\nHeader\n\nTeam Report\n\nFooter'"
    })
    void exportShouldLogCorrectOutput(ReportType type, Class<ExportStrategy> strategy, String expected) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Report report = new ReportBuilder(new Sprint())
                .exportStrategy(strategy.getDeclaredConstructor().newInstance())
                .type(type)
                .header("Header")
                .footer("Footer")
                .build();

        report.export();

        assertThat(logCaptor.getInfoLogs()).containsExactly(expected);
    }
}
