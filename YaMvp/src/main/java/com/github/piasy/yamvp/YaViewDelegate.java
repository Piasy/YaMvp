package com.github.piasy.yamvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Piasy{github.com/Piasy} on 17/09/2016.
 */

public abstract class YaViewDelegate<V extends YaView, P extends YaPresenter<V>> {
    private P mPresenter;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        checkPresenter();
    }

    public void onStart(V view) {
        checkPresenter();
        mPresenter.attachView(view);
    }

    public void onStop() {
        checkPresenter();
        mPresenter.detachView();
    }

    public void onDestroy() {
        checkPresenter();
        mPresenter.onDestroy();
    }

    protected abstract P createPresenter();

    private void checkPresenter() {
        if (mPresenter == null) {
            throw new IllegalStateException(
                    "You must call YaViewDelegate#onCreate! And createPresenter must return "
                    + "non-null");
        }
    }
}
