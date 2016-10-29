package com.github.piasy.yamvp.rx;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by Piasy{github.com/Piasy} on 18/09/2016.
 */
public class YaRxPresenterTest {

    private TestRxPresenter mTestRxPresenter;
    private TestView mTestView;
    private Disposable mDisposable;

    @Before
    public void setUp() throws Exception {
        mTestRxPresenter = new TestRxPresenter();
        mTestView = new TestView() {
        };
        mDisposable = Flowable.interval(1, TimeUnit.SECONDS)
                .subscribe();
    }

    @Test
    public void addUtilStop() throws Exception {
        mTestRxPresenter.attachView(mTestView);
        mTestRxPresenter.addUtilStop(mDisposable);
        assertThat(mDisposable.isDisposed()).isFalse();
        mTestRxPresenter.detachView();
        assertThat(mDisposable.isDisposed()).isTrue();
    }

    @Test
    public void addUtilDestroy() throws Exception {
        mTestRxPresenter.attachView(mTestView);
        mTestRxPresenter.addUtilDestroy(mDisposable);
        assertThat(mDisposable.isDisposed()).isFalse();
        mTestRxPresenter.detachView();
        mTestRxPresenter.onDestroy();
        assertThat(mDisposable.isDisposed()).isTrue();
    }

    @Test
    public void remove() throws Exception {
        mTestRxPresenter.attachView(mTestView);
        mTestRxPresenter.addUtilStop(mDisposable);
        assertThat(mDisposable.isDisposed()).isFalse();
        mTestRxPresenter.remove(mDisposable);
        mTestRxPresenter.detachView();
        assertThat(mDisposable.isDisposed()).isTrue();
    }
}