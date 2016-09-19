package com.github.piasy.yamvp;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

/**
 * Created by Piasy{github.com/Piasy} on 17/09/2016.
 */

public abstract class YaPresenter<V extends YaView> {

    private V mView;

    @CallSuper
    public void attachView(V view) {
        mView = view;
    }

    @CallSuper
    public void detachView() {
        mView = null;
    }

    @CallSuper
    public void onDestroy() {
        if (isViewAttached()) {
            throw new IllegalStateException("View should been detached before destroy!");
        }
    }

    protected boolean isViewAttached() {
        return mView != null;
    }

    @NonNull
    protected V getView() {
        if (mView == null) {
            throw new IllegalStateException("View has been detached!");
        }
        return mView;
    }
}
