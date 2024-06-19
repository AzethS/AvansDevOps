package com.avansdevops;

import com.avansdevops.notifications.strategy.NotificationStrategy;
import com.avansdevops.pipeline.Pipeline;
import com.avansdevops.sprint.ReleaseSprint;
import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.states.SprintStateType;
import com.avansdevops.user.Role;
import com.avansdevops.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SprintTests {

    @Test
    void addParticipantShouldSucceed() {
        Sprint sprint = new Sprint();
        User user = new User("John", Role.DEVELOPER);
        User user2 = new User("Jane", Role.DEVELOPER);
        User user3 = new User("Jack", Role.SCRUM_MASTER);

        Assertions.assertDoesNotThrow(() -> sprint.addParticipant(user));
        Assertions.assertDoesNotThrow(() -> sprint.addParticipant(user2));
        Assertions.assertDoesNotThrow(() -> sprint.addParticipant(user3));
    }

    @Test
    void addParticipantShouldFailIfAlreadyExists() {
        Sprint sprint = new Sprint();
        User user = new User("John");
        sprint.addParticipant(user);

        Assertions.assertThrows(IllegalArgumentException.class, () -> sprint.addParticipant(user));
    }

    @Test
    void addParticipantScrumMasterShouldFailIfAlreadyExists() {
        Sprint sprint = new Sprint();
        User user = new User("John", Role.SCRUM_MASTER);
        sprint.addParticipant(user);

        User user2 = new User("Jane", Role.SCRUM_MASTER);
        Assertions.assertThrows(IllegalArgumentException.class, () -> sprint.addParticipant(user2));
    }

    @Test
    void addParticipantProductOwnerShouldFailIfAlreadyExists() {
        Sprint sprint = new Sprint();
        User user = new User("John", Role.PRODUCT_OWNER);
        sprint.addParticipant(user);

        User user2 = new User("Jane", Role.PRODUCT_OWNER);
        Assertions.assertThrows(IllegalArgumentException.class, () -> sprint.addParticipant(user2));
    }

    @Test
    void releaseSprintMovedToFinishedShouldStartReleaseAction() {
        Pipeline pipeline = Mockito.mock(Pipeline.class);
        Sprint sprint = new ReleaseSprint(pipeline);
        sprint.setState(SprintStateType.IN_REVIEW.create(sprint));

        sprint.getState().transferToFinished();

        Mockito.verify(pipeline, Mockito.times(1)).run();
    }

    @Test
    void sprintMovedToFailShouldNotifyProductOwnerAndScrumMaster() {
        Sprint sprint = new Sprint();
        sprint.setState(SprintStateType.IN_REVIEW.create(sprint));

        NotificationStrategy productOwnerStrategy = Mockito.mock(NotificationStrategy.class);
        NotificationStrategy scrumMasterStrategy = Mockito.mock(NotificationStrategy.class);
        NotificationStrategy testerStrategy = Mockito.mock(NotificationStrategy.class);

        User productOwner = new User("ProductOwner", Role.PRODUCT_OWNER, productOwnerStrategy);
        User scrumMaster = new User("ScrumMaster", Role.SCRUM_MASTER, scrumMasterStrategy);
        User tester = new User("Tester", Role.TESTER, testerStrategy);

        sprint.addParticipant(productOwner);
        sprint.addParticipant(scrumMaster);
        sprint.addParticipant(tester);

        sprint.getState().transferToFailed();

        Mockito.verify(productOwnerStrategy, Mockito.times(1)).sendNotification(Mockito.anyString());
        Mockito.verify(scrumMasterStrategy, Mockito.times(1)).sendNotification(Mockito.anyString());
        Mockito.verify(testerStrategy, Mockito.times(0)).sendNotification(Mockito.anyString());
    }

    @ParameterizedTest
    @CsvSource({
            "transferToPlanned, true",
            "transferToInProgress, false",
            "transferToInReview, true",
            "transferToFinished, true",
            "transferToFailed, true"
    })
    void transferringFromPlannedToInvalidStateShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();

        assertThrows(sprint.getState(), methodName, shouldThrow);
    }

    @ParameterizedTest
    @CsvSource({
            "transferToPlanned, true",
            "transferToInProgress, true",
            "transferToInReview, false",
            "transferToFinished, false",
            "transferToFailed, true"
    })
    void transferringFromInProgressToInvalidStateShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        sprint.setState(SprintStateType.IN_PROGRESS.create(sprint));

        assertThrows(sprint.getState(), methodName, shouldThrow);
    }

    @ParameterizedTest
    @CsvSource({
            "transferToPlanned, true",
            "transferToInProgress, true",
            "transferToInReview, true",
            "transferToFinished, false",
            "transferToFailed, false"
    })
    void transferringFromInReviewToInvalidStateShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        sprint.setState(SprintStateType.IN_REVIEW.create(sprint));

        assertThrows(sprint.getState(), methodName, shouldThrow);
    }

    @ParameterizedTest
    @CsvSource({
            "transferToPlanned, true",
            "transferToInProgress, true",
            "transferToInReview, true",
            "transferToFinished, true",
            "transferToFailed, true"
    })
    void transferringFromFinishedShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        sprint.setState(SprintStateType.FINISHED.create(sprint));

        assertThrows(sprint.getState(), methodName, shouldThrow);
    }

    @ParameterizedTest
    @CsvSource({
            "transferToPlanned, true",
            "transferToInProgress, true",
            "transferToInReview, true",
            "transferToFinished, true",
            "transferToFailed, true"
    })
    void transferringFromFailedShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        sprint.setState(SprintStateType.FAILED.create(sprint));

        assertThrows(sprint.getState(), methodName, shouldThrow);
    }

    private static void assertThrows(Object obj, String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Method method = obj.getClass().getMethod(methodName);
        if (shouldThrow) {
            // Method#invoke throws an InvocationTargetException when the underlying method throws an exception.
            Assertions.assertThrows(InvocationTargetException.class, () -> method.invoke(obj));
        } else {
            Assertions.assertDoesNotThrow(() -> method.invoke(obj));
        }
    }
}
