package com.github.piasy.yamvp.rx;

import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.Subscription;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by Piasy{github.com/Piasy} on 18/09/2016.
 */
public class YaRxPresenterTest {

    private TestRxPresenter mTestRxPresenter;
    private TestView mTestView;
    private Subscription mSubscription;

    @Before
    public void setUp() throws Exception {
        mTestRxPresenter = new TestRxPresenter();
        mTestView = new TestView() {
        };
        mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe();
    }

    @Test
    public void addUtilStop() throws Exception {
        mTestRxPresenter.attachView(mTestView);
        mTestRxPresenter.addUtilStop(mSubscription);
        assertThat(mSubscription.isUnsubscribed()).isFalse();
        mTestRxPresenter.detachView();
        assertThat(mSubscription.isUnsubscribed()).isTrue();
    }

    @Test
    public void addUtilDestroy() throws Exception {
        mTestRxPresenter.attachView(mTestView);
        mTestRxPresenter.addUtilDestroy(mSubscription);
        assertThat(mSubscription.isUnsubscribed()).isFalse();
        mTestRxPresenter.detachView();
        mTestRxPresenter.onDestroy();
        assertThat(mSubscription.isUnsubscribed()).isTrue();
    }

    @Test
    public void remove() throws Exception {
        mTestRxPresenter.attachView(mTestView);
        mTestRxPresenter.addUtilStop(mSubscription);
        assertThat(mSubscription.isUnsubscribed()).isFalse();
        mTestRxPresenter.remove(mSubscription);
        mTestRxPresenter.detachView();
        assertThat(mSubscription.isUnsubscribed()).isTrue();
    }
}