package com.avansdevops;

import com.avansdevops.pipeline.Pipeline;
import com.avansdevops.pipeline.actions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;

class PipelineTests {

    @ParameterizedTest
    @CsvSource(value = {
            "false, true",
            "true, false"
    })
    void testActionWithFailingComponentShouldFail(boolean succeedsTest, boolean succeedsTestPublish) {
        TestAction mock = Mockito.spy(new TestAction("jUnit"));

        Mockito.when(mock.runTests()).thenReturn(succeedsTest);
        Mockito.when(mock.publishTestResults()).thenReturn(succeedsTestPublish);

        Assertions.assertFalse(mock.execute());
    }

    @Test
    void packageActionShouldFailIfAnyPackageFails() {
        PackageAction mock = Mockito.spy(new PackageAction(List.of(
                "com.google.code.gson:gson:2.10.1",
                "junit:junit:4.13.2"
        )));

        Mockito.when(mock.installPackage(Mockito.anyString())).thenReturn(false);

        Assertions.assertFalse(mock.execute());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "false, true, true, true, true, true, true",
            "true, false, true, true, true, true, true",
            "true, true, false, true, true, true, true",
            "true, true, true, false, true, true, true",
            "true, true, true, true, false, true, true",
            "true, true, true, true, true, false, true",
            "true, true, true, true, true, true, false"
    })
    void pipelineWithAnyFailingActionShouldFail(boolean sourcesAction, boolean packageAction, boolean buildAction, boolean testAction, boolean analyseAction, boolean deployAction, boolean utilityAction) {
        Pipeline pipeline = new Pipeline();

        pipeline.addAction(mockAction(SourcesAction.class, sourcesAction));
        pipeline.addAction(mockAction(PackageAction.class, packageAction));
        pipeline.addAction(mockAction(BuildAction.class, buildAction));
        pipeline.addAction(mockAction(TestAction.class, testAction));
        pipeline.addAction(mockAction(AnalyseAction.class, analyseAction));
        pipeline.addAction(mockAction(DeployAction.class, deployAction));
        pipeline.addAction(mockAction(UtilityAction.class, utilityAction));

        Assertions.assertFalse(pipeline.run());
    }

    @Test
    void pipelineWithoutFailingActionShouldSucceed() {
        Pipeline pipeline = new Pipeline();

        pipeline.addAction(mockAction(SourcesAction.class, true));
        pipeline.addAction(mockAction(PackageAction.class, true));
        pipeline.addAction(mockAction(BuildAction.class, true));
        pipeline.addAction(mockAction(TestAction.class, true));
        pipeline.addAction(mockAction(AnalyseAction.class, true));
        pipeline.addAction(mockAction(DeployAction.class, true));
        pipeline.addAction(mockAction(UtilityAction.class, true));

        Assertions.assertTrue(pipeline.run());
    }

    private <T extends Action> Action mockAction(Class<T> clazz, boolean success) {
        Action mock = Mockito.mock(clazz);
        Mockito.when(mock.execute()).thenReturn(success);
        return mock;
    }
}
