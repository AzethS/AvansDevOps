package com.avansdevops;

import com.avansdevops.notifications.strategy.NotificationStrategy;
import com.avansdevops.user.Role;
import com.avansdevops.user.User;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests {
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
            "com.avansdevops.notifications.strategy.EmailNotificationStrategy, Sending Email notification: Hello World",
            "com.avansdevops.notifications.strategy.SlackNotificationStrategy, Sending Slack notification: Hello World",
            "com.avansdevops.notifications.strategy.SmsNotificationStrategy, Sending SMS notification: Hello World"
    })
    void notificationShouldLogCorrectOutput(Class<NotificationStrategy> strategy, String expectedStart) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        User user = new User("John", Role.UNKNOWN, strategy.getDeclaredConstructor().newInstance());
        user.update("Hello World");

        assertThat(logCaptor.getInfoLogs()).containsExactly(expectedStart);
    }
}