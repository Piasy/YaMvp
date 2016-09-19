package com.github.piasy.yamvp.example;

import android.support.test.InstrumentationRegistry;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.azimolabs.conditionwatcher.Instruction;

/**
 * Created by Piasy{github.com/Piasy} on 19/09/2016.
 */

public class FragmentResumedInstruction extends Instruction {
    @Override
    public String getDescription() {
        return "wait for MainFragment to resume";
    }

    @Override
    public boolean checkCondition() {
        AppCompatActivity activity =
                (AppCompatActivity) ((TestApplication) InstrumentationRegistry.getTargetContext()
                        .getApplicationContext()).getCurrentActivity();
        Fragment fragment =
                activity.getSupportFragmentManager().findFragmentById(android.R.id.content);
        return fragment.isResumed();
    }
}
