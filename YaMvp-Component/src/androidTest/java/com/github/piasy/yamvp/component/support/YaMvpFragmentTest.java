package com.github.piasy.yamvp.component.support;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.azimolabs.conditionwatcher.ConditionWatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by Piasy{github.com/Piasy} on 18/09/2016.
 */

@RunWith(AndroidJUnit4.class)
public class YaMvpFragmentTest {
    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();

    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule =
            new ActivityTestRule<>(TestActivity.class, true, false);

    @Mock
    private TestPresenter mTestPresenter;

    private TestFragment mTestFragment;

    @Before
    public void setUp() throws Exception {
        TestActivity activity = mActivityTestRule.launchActivity(new Intent());
        activity.startTestFragment(mTestPresenter);
        mTestFragment = activity.getTestFragment();
        ConditionWatcher.waitForCondition(new TestFragmentStartedInstruction(mTestFragment));
    }

    @Test
    public void onStart() throws Exception {
        verify(mTestPresenter, only()).attachView(mTestFragment);
    }

    @Test
    public void onStop() throws Exception {
        mTestFragment.onStop();
        verify(mTestPresenter, times(1)).attachView(mTestFragment);
        verify(mTestPresenter, times(1)).detachView();
        verifyNoMoreInteractions(mTestPresenter);
    }

    @Test
    public void onDestroy() throws Exception {
        mTestFragment.onStop();
        mTestFragment.onDestroy();
        verify(mTestPresenter, times(1)).attachView(mTestFragment);
        verify(mTestPresenter, times(1)).detachView();
        verify(mTestPresenter, times(1)).onDestroy();
        verifyNoMoreInteractions(mTestPresenter);
    }
}