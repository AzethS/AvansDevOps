package com.avansdevops.sprint;

import com.avansdevops.pipeline.Pipeline;

public class ReleaseSprint extends Sprint {
    private final Pipeline releasePipeline;

    public ReleaseSprint(Pipeline releasePipeline) {
        this.releasePipeline = releasePipeline;
    }

    @Override
    public void onFinished() {
        super.onFinished();
        this.releasePipeline.execute();
    }
}
