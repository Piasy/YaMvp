package com.github.piasy.yamvp.rx;

import javax.annotation.concurrent.ThreadSafe;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Piasy{github.com/Piasy} on 17/09/2016.
 */

@ThreadSafe
public class YaRxDelegate {
    private CompositeSubscription mSubscriptions2Stop;
    private CompositeSubscription mSubscriptions2Destroy;

    public synchronized boolean addUtilStop(Subscription subscription) {
        if (mSubscriptions2Stop == null) {
            throw new IllegalStateException(
                    "addUtilStop should be called between onStart and onStop");
        }
        mSubscriptions2Stop.add(subscription);
        return true;
    }

    public synchronized boolean addUtilDestroy(Subscription subscription) {
        if (mSubscriptions2Destroy == null) {
            throw new IllegalStateException(
                    "addUtilDestroy should be called between onCreate and onDestroy");
        }
        mSubscriptions2Destroy.add(subscription);
        return true;
    }

    public synchronized void remove(Subscription subscription) {
        if (mSubscriptions2Stop == null && mSubscriptions2Destroy == null) {
            throw new IllegalStateException("remove should not be called after onDestroy");
        }
        if (mSubscriptions2Stop != null) {
            mSubscriptions2Stop.remove(subscription);
        }
        if (mSubscriptions2Destroy != null) {
            mSubscriptions2Destroy.remove(subscription);
        }
    }

    public synchronized void onCreate() {
        if (mSubscriptions2Destroy != null) {
            throw new IllegalStateException("onCreate called multiple times");
        }
        mSubscriptions2Destroy = new CompositeSubscription();
    }

    public synchronized void onStart() {
        if (mSubscriptions2Stop != null) {
            throw new IllegalStateException("onStart called multiple times");
        }
        mSubscriptions2Stop = new CompositeSubscription();
    }

    public synchronized void onStop() {
        if (mSubscriptions2Stop == null) {
            throw new IllegalStateException("onStop called multiple times or onStart not called");
        }
        mSubscriptions2Stop.unsubscribe();
        mSubscriptions2Stop = null;
    }

    public synchronized void onDestroy() {
        if (mSubscriptions2Destroy == null) {
            throw new IllegalStateException(
                    "onDestroy called multiple times or onCreate not called");
        }
        mSubscriptions2Destroy.unsubscribe();
        mSubscriptions2Destroy = null;
    }
}
