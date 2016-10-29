package com.github.piasy.yamvp.rx;

import com.github.piasy.yamvp.YaPresenter;
import com.github.piasy.yamvp.YaView;
import io.reactivex.disposables.Disposable;
import rx.Subscription;

/**
 * Created by Piasy{github.com/Piasy} on 17/09/2016.
 */

public abstract class YaRxPresenter<V extends YaView> extends YaPresenter<V> {

    private final YaRxDelegate mYaRxDelegate;

    protected YaRxPresenter() {
        mYaRxDelegate = new YaRxDelegate();
        mYaRxDelegate.onCreate();
    }

    protected boolean addUtilStop(Subscription subscription) {
        return mYaRxDelegate.addUtilStop(subscription);
    }

    public boolean addUtilDestroy(Subscription subscription) {
        return mYaRxDelegate.addUtilDestroy(subscription);
    }

    public void remove(Subscription subscription) {
        mYaRxDelegate.remove(subscription);
    }

    protected boolean addUtilStop(Disposable disposable) {
        return mYaRxDelegate.addUtilStop(disposable);
    }

    public boolean addUtilDestroy(Disposable disposable) {
        return mYaRxDelegate.addUtilDestroy(disposable);
    }

    public void remove(Disposable disposable) {
        mYaRxDelegate.remove(disposable);
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
        mYaRxDelegate.onStart();
    }

    @Override
    public void detachView() {
        super.detachView();
        mYaRxDelegate.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mYaRxDelegate.onDestroy();
    }
}
