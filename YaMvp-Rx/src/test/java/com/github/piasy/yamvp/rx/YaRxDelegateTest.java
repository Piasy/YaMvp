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
public class YaRxDelegateTest {
    private YaRxDelegate mYaRxDelegate;
    private Subscription mSubscription;

    @Before
    public void setUp() throws Exception {
        mYaRxDelegate = new YaRxDelegate();
        mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe();
    }

    @Test
    public void addUtilStop() throws Exception {
        mYaRxDelegate.onCreate();
        mYaRxDelegate.onStart();
        mYaRxDelegate.addUtilStop(mSubscription);
        assertThat(mSubscription.isUnsubscribed()).isFalse();
        mYaRxDelegate.onStop();
        assertThat(mSubscription.isUnsubscribed()).isTrue();
    }

    @Test
    public void addUtilDestroy() throws Exception {
        mYaRxDelegate.onCreate();
        mYaRxDelegate.onStart();
        mYaRxDelegate.addUtilDestroy(mSubscription);
        assertThat(mSubscription.isUnsubscribed()).isFalse();
        mYaRxDelegate.onDestroy();
        assertThat(mSubscription.isUnsubscribed()).isTrue();
    }

    @Test
    public void addUtilDestroy_stop_not_unsubscribe() throws Exception {
        mYaRxDelegate.onCreate();
        mYaRxDelegate.onStart();
        mYaRxDelegate.addUtilDestroy(mSubscription);
        assertThat(mSubscription.isUnsubscribed()).isFalse();
        mYaRxDelegate.onStop();
        assertThat(mSubscription.isUnsubscribed()).isFalse();
    }

    @Test
    public void remove() throws Exception {
        mYaRxDelegate.onCreate();
        mYaRxDelegate.onStart();
        mYaRxDelegate.addUtilStop(mSubscription);
        assertThat(mSubscription.isUnsubscribed()).isFalse();
        mYaRxDelegate.remove(mSubscription);
        mYaRxDelegate.onStop();
        assertThat(mSubscription.isUnsubscribed()).isTrue();
    }

    @Test
    public void onCreate() throws Exception {
        mYaRxDelegate.onCreate();
    }

    @Test(expected = IllegalStateException.class)
    public void onCreate_multiple_times() throws Exception {
        mYaRxDelegate.onCreate();
        mYaRxDelegate.onCreate();
    }

    @Test
    public void onCreate_after_destroy() throws Exception {
        mYaRxDelegate.onCreate();
        mYaRxDelegate.onDestroy();
        mYaRxDelegate.onCreate();
    }

    @Test
    public void onStart() throws Exception {
        mYaRxDelegate.onStart();
    }

    @Test(expected = IllegalStateException.class)
    public void onStart_multiple_times() throws Exception {
        mYaRxDelegate.onStart();
        mYaRxDelegate.onStart();
    }

    @Test
    public void onStart_after_stop() throws Exception {
        mYaRxDelegate.onStart();
        mYaRxDelegate.onStop();
        mYaRxDelegate.onStart();
    }

    @Test(expected = IllegalStateException.class)
    public void onStart_after_destroy() throws Exception {
        mYaRxDelegate.onStart();
        mYaRxDelegate.onDestroy();
        mYaRxDelegate.onStart();
    }

    @Test
    public void onStop() throws Exception {
        mYaRxDelegate.onStart();
        mYaRxDelegate.onStop();
    }

    @Test(expected = IllegalStateException.class)
    public void onStop_before_start() throws Exception {
        mYaRxDelegate.onStop();
    }

    @Test(expected = IllegalStateException.class)
    public void onStop_multiple_times() throws Exception {
        mYaRxDelegate.onStart();
        mYaRxDelegate.onStop();
        mYaRxDelegate.onStop();
    }

    @Test
    public void onDestroy() throws Exception {
        mYaRxDelegate.onCreate();
        mYaRxDelegate.onDestroy();
    }

    @Test(expected = IllegalStateException.class)
    public void onDestroy_before_create() throws Exception {
        mYaRxDelegate.onDestroy();
    }

    @Test(expected = IllegalStateException.class)
    public void onDestroy_multiple_times() throws Exception {
        mYaRxDelegate.onCreate();
        mYaRxDelegate.onDestroy();
        mYaRxDelegate.onDestroy();
    }
}