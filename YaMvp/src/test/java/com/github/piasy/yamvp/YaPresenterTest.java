package com.github.piasy.yamvp;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by Piasy{github.com/Piasy} on 18/09/2016.
 */
public class YaPresenterTest {
    private TestPresenter mTestPresenter;
    private TestView mTestView;

    @Before
    public void setUp() throws Exception {
        mTestPresenter = new TestPresenter();
        mTestView = new TestView() {
        };
    }

    @Test
    public void isView_attached() throws Exception {
        assertThat(mTestPresenter.isViewAttached()).isFalse();
        mTestPresenter.attachView(mTestView);
        assertThat(mTestPresenter.isViewAttached()).isTrue();
        mTestPresenter.detachView();
        assertThat(mTestPresenter.isViewAttached()).isFalse();
        mTestPresenter.onDestroy();
        assertThat(mTestPresenter.isViewAttached()).isFalse();
    }

    @Test
    public void getView_attached() throws Exception {
        mTestPresenter.attachView(mTestView);
        assertThat(mTestPresenter.getView()).isSameAs(mTestView);
    }

    @Test(expected = IllegalStateException.class)
    public void getView_not_attached() throws Exception {
        mTestPresenter.getView();
    }

    @Test
    public void onDestroy_not_attached() throws Exception {
        mTestPresenter.onDestroy();
    }

    @Test
    public void onDestroy_detached() throws Exception {
        mTestPresenter.attachView(mTestView);
        mTestPresenter.detachView();
        mTestPresenter.onDestroy();
    }

    @Test(expected = IllegalStateException.class)
    public void onDestroy_not_detached() throws Exception {
        mTestPresenter.attachView(mTestView);
        mTestPresenter.onDestroy();
    }
}