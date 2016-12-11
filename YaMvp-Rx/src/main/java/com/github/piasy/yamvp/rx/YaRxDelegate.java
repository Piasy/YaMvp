package com.github.piasy.yamvp.rx;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Created by Piasy{github.com/Piasy} on 17/09/2016.
 */

@ThreadSafe
public class YaRxDelegate {
    private CompositeDisposable mDisposables2Stop;
    private CompositeDisposable mDisposables2Destroy;

    public synchronized boolean addUtilStop(Disposable disposable) {
        if (mDisposables2Stop == null) {
            throw new IllegalStateException(
                    "addUtilStop should be called between onStart and onStop");
        }
        mDisposables2Stop.add(disposable);
        return true;
    }

    public synchronized boolean addUtilDestroy(Disposable disposable) {
        if (mDisposables2Destroy == null) {
            throw new IllegalStateException(
                    "addUtilDestroy should be called between onCreate and onDestroy");
        }
        mDisposables2Destroy.add(disposable);
        return true;
    }

    public synchronized void remove(Disposable disposable) {
        if (mDisposables2Stop == null && mDisposables2Destroy == null) {
            throw new IllegalStateException("remove should not be called after onDestroy");
        }
        if (mDisposables2Stop != null) {
            mDisposables2Stop.remove(disposable);
        }
        if (mDisposables2Destroy != null) {
            mDisposables2Destroy.remove(disposable);
        }
    }

    public synchronized void onCreate() {
        if (mDisposables2Destroy != null) {
            throw new IllegalStateException("onCreate called multiple times");
        }
        mDisposables2Destroy = new CompositeDisposable();
    }

    public synchronized void onStart() {
        if (mDisposables2Stop != null) {
            throw new IllegalStateException("onStart called multiple times");
        }
        mDisposables2Stop = new CompositeDisposable();
    }

    public synchronized void onStop() {
        if (mDisposables2Stop == null) {
            throw new IllegalStateException("onStop called multiple times or onStart not called");
        }
        mDisposables2Stop.dispose();
        mDisposables2Stop = null;
    }

    public synchronized void onDestroy() {
        if (mDisposables2Destroy == null) {
            throw new IllegalStateException(
                    "onDestroy called multiple times or onCreate not called");
        }
        mDisposables2Destroy.dispose();
        mDisposables2Destroy = null;
    }
}
