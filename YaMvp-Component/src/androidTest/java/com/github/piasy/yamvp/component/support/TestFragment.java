package com.github.piasy.yamvp.component.support;

/**
 * Created by Piasy{github.com/Piasy} on 19/09/2016.
 */

public class TestFragment extends YaMvpFragment<TestView, TestPresenter> implements TestView {

    private TestPresenter mTestPresenter;


    void setTestPresenter(TestPresenter testPresenter) {
        mTestPresenter = testPresenter;
    }

    @Override
    protected TestPresenter createPresenter() {
        return mTestPresenter;
    }
}
