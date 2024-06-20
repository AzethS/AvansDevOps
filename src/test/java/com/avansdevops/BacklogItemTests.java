package com.avansdevops;

import com.avansdevops.notifications.strategy.NotificationStrategy;
import com.avansdevops.sprint.Sprint;
import com.avansdevops.sprint.backlog.Activity;
import com.avansdevops.sprint.backlog.BacklogItem;
import com.avansdevops.sprint.backlog.states.BacklogItemState;
import com.avansdevops.sprint.backlog.states.BacklogItemStateType;
import com.avansdevops.user.Role;
import com.avansdevops.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class BacklogItemTests {

    @Test
    void backlogItemWithDoneStateShouldBeDone() {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item | Done");
        item.setState(BacklogItemStateType.DONE.create(item));

        Assertions.assertTrue(item.isDone());
    }

    @Test
    void backlogItemMovedToReadyForTestingShouldNotifyTesters() {
        MockedBacklogItem mock = createMockedBacklogItemWithState(BacklogItemStateType.DOING);

        mock.item.getState().transferToReadyForTesting();

        Mockito.verify(mock.tester, Mockito.times(1)).sendNotification(Mockito.anyString());

        Mockito.verify(mock.leadDeveloper, Mockito.times(0)).sendNotification(Mockito.anyString());
        Mockito.verify(mock.scrumMaster, Mockito.times(0)).sendNotification(Mockito.anyString());
    }

    @Test
    void backlogItemMovedFromTestingToTodoShouldNotifyScrumMaster() {
        MockedBacklogItem mock = createMockedBacklogItemWithState(BacklogItemStateType.TESTING);

        mock.item.getState().transferToTodo();

        Mockito.verify(mock.scrumMaster, Mockito.times(1)).sendNotification(Mockito.anyString());

        Mockito.verify(mock.leadDeveloper, Mockito.times(0)).sendNotification(Mockito.anyString());
        Mockito.verify(mock.tester, Mockito.times(0)).sendNotification(Mockito.anyString());
    }

    @Test
    void backlogItemMovedToTestedShouldNotifyLeadDevelopers() {
        MockedBacklogItem mock = createMockedBacklogItemWithState(BacklogItemStateType.TESTING);

        mock.item.getState().transferToTested();

        Mockito.verify(mock.leadDeveloper, Mockito.times(1)).sendNotification(Mockito.anyString());

        Mockito.verify(mock.scrumMaster, Mockito.times(0)).sendNotification(Mockito.anyString());
        Mockito.verify(mock.tester, Mockito.times(0)).sendNotification(Mockito.anyString());
    }

    @Test
    void backlogItemAssignNonDeveloperUserShouldFail() {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item");

        User user = new User("Tester", Role.TESTER);
        Assertions.assertThrows(IllegalArgumentException.class, () -> item.setAssignedUser(user));
    }

    @Test
    void activityAssignNonDeveloperUserShouldFail() {
        Activity activity = new Activity("Test Activity");

        User user = new User("Tester", Role.TESTER);
        Assertions.assertThrows(IllegalArgumentException.class, () -> activity.setAssignedUser(user));
    }

    @Test
    void transferringFromDoingToReadyForTestingShouldFailIfActivitiesNotFinished() {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item");
        item.setState(BacklogItemStateType.DOING.create(item));

        Activity unfinishedActivity = new Activity("Unfinished Activity");
        Activity finishedActivity = new Activity("Finished Activity");

        item.addActivity(finishedActivity);
        item.addActivity(unfinishedActivity);

        finishedActivity.finish();

        BacklogItemState state = item.getState();
        Assertions.assertThrows(IllegalStateException.class, state::transferToReadyForTesting);
    }

    @Test
    void transferringFromDoingToReadyForTestingShouldSucceedIfActivitiesHaveFinished() {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item");
        item.setState(BacklogItemStateType.DOING.create(item));

        Activity finishedActivity = new Activity("Finished Activity");
        Activity finishedActivity2 = new Activity("Finished Activity 2");

        item.addActivity(finishedActivity);
        item.addActivity(finishedActivity2);

        finishedActivity.finish();
        finishedActivity2.finish();

        Assertions.assertDoesNotThrow(() -> item.getState().transferToReadyForTesting());
    }

    @ParameterizedTest
    @CsvSource({
            "transferToTodo, true",
            "transferToDoing, false",
            "transferToReadyForTesting, true",
            "transferToTesting, true",
            "transferToTested, true",
            "transferToDone, true"
    })
    void transferringFromTodoToInvalidStateShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item | Todo");

        assertThrows(item.getState(), methodName, shouldThrow);
    }

    @ParameterizedTest
    @CsvSource({
            "transferToTodo, false",
            "transferToDoing, true",
            "transferToReadyForTesting, false",
            "transferToTesting, true",
            "transferToTested, true",
            "transferToDone, true"
    })
    void transferringFromDoingToInvalidStateShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item | Doing");
        item.setState(BacklogItemStateType.DOING.create(item));

        assertThrows(item.getState(), methodName, shouldThrow);
    }

    @ParameterizedTest
    @CsvSource({
            "transferToTodo, true",
            "transferToDoing, true",
            "transferToReadyForTesting, true",
            "transferToTesting, false",
            "transferToTested, true",
            "transferToDone, true"
    })
    void transferringFromReadyForTestingToInvalidStateShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item | ReadyForTesting");
        item.setState(BacklogItemStateType.READY_FOR_TESTING.create(item));

        assertThrows(item.getState(), methodName, shouldThrow);
    }

    @ParameterizedTest
    @CsvSource({
            "transferToTodo, false",
            "transferToDoing, true",
            "transferToReadyForTesting, true",
            "transferToTesting, true",
            "transferToTested, false",
            "transferToDone, true"
    })
    void transferringFromTestingToInvalidStateShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item | Testing");
        item.setState(BacklogItemStateType.TESTING.create(item));

        assertThrows(item.getState(), methodName, shouldThrow);
    }

    @ParameterizedTest
    @CsvSource({
            "transferToTodo, true",
            "transferToDoing, true",
            "transferToReadyForTesting, false",
            "transferToTesting, true",
            "transferToTested, true",
            "transferToDone, false"
    })
    void transferringFromTestedToInvalidStateShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item | Tested");
        item.setState(BacklogItemStateType.TESTED.create(item));

        assertThrows(item.getState(), methodName, shouldThrow);
    }

    @ParameterizedTest
    @CsvSource({
            "transferToTodo, true",
            "transferToDoing, true",
            "transferToReadyForTesting, true",
            "transferToTesting, true",
            "transferToTested, true",
            "transferToDone, true"
    })
    void transferringFromDoneShouldFail(String methodName, boolean shouldThrow) throws NoSuchMethodException {
        Sprint sprint = new Sprint();
        BacklogItem item = sprint.addBacklogItem("Test Item | Done");
        item.setState(BacklogItemStateType.DONE.create(item));

        assertThrows(item.getState(), methodName, shouldThrow);
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

    private static MockedBacklogItem createMockedBacklogItemWithState(BacklogItemStateType stateType) {
        Sprint sprint = new Sprint();

        NotificationStrategy leadDeveloperStrategy = Mockito.mock(NotificationStrategy.class);
        NotificationStrategy scrumMasterStrategy = Mockito.mock(NotificationStrategy.class);
        NotificationStrategy testerStrategy = Mockito.mock(NotificationStrategy.class);

        User leadDeveloper = new User("LeadDeveloper", Role.LEAD_DEVELOPER, leadDeveloperStrategy);
        User scrumMaster = new User("ScrumMaster", Role.SCRUM_MASTER, scrumMasterStrategy);
        User tester = new User("Tester", Role.TESTER, testerStrategy);

        sprint.addParticipant(leadDeveloper);
        sprint.addParticipant(scrumMaster);
        sprint.addParticipant(tester);

        BacklogItem item = sprint.addBacklogItem("Backlog Item");
        item.setState(stateType.create(item));
        return new MockedBacklogItem(item, leadDeveloperStrategy, scrumMasterStrategy, testerStrategy);
    }

    private record MockedBacklogItem(
            BacklogItem item,
            NotificationStrategy leadDeveloper,
            NotificationStrategy scrumMaster,
            NotificationStrategy tester) {
    }
}
