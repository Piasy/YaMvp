package com.github.piasy.yamvp.component.support;

import com.azimolabs.conditionwatcher.Instruction;

/**
 * Created by Piasy{github.com/Piasy} on 19/09/2016.
 */

class TestFragmentStartedInstruction extends Instruction {
    private final TestFragment mTestFragment;

    TestFragmentStartedInstruction(TestFragment testFragment) {
        mTestFragment = testFragment;
    }

    @Override
    public String getDescription() {
        return "Wait TestFragment to start";
    }

    @Override
    public boolean checkCondition() {
        return mTestFragment.isResumed();
    }
}
