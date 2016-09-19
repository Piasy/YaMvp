package com.github.piasy.yamvp;

import android.os.Bundle;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Piasy{github.com/Piasy} on 18/09/2016.
 */
public class YaViewDelegateTest {

    private YaViewDelegate<TestView, TestPresenter> mYaViewDelegate;
    private TestView mTestView;

    @Before
    public void setUp() throws Exception {
        mYaViewDelegate = new YaViewDelegate<TestView, TestPresenter>() {
            @Override
            protected TestPresenter createPresenter() {
                return new TestPresenter();
            }
        };
        mTestView = new TestView() {
        };
    }

    @Test
    public void onStart_with_create() throws Exception {
        mYaViewDelegate.onCreate(new Bundle());
        mYaViewDelegate.onStart(mTestView);
    }

    @Test(expected = IllegalStateException.class)
    public void onStart_without_create() throws Exception {
        mYaViewDelegate.onStart(mTestView);
    }

    @Test
    public void onStop_with_create() throws Exception {
        mYaViewDelegate.onCreate(new Bundle());
        mYaViewDelegate.onStop();
    }

    @Test(expected = IllegalStateException.class)
    public void onStop_without_create() throws Exception {
        mYaViewDelegate.onStop();
    }

    @Test
    public void onDestroy_with_create() throws Exception {
        mYaViewDelegate.onCreate(new Bundle());
        mYaViewDelegate.onDestroy();
    }

    @Test(expected = IllegalStateException.class)
    public void onDestroy_without_create() throws Exception {
        mYaViewDelegate.onDestroy();
    }
}