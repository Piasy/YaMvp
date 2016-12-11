package com.github.piasy.yamvp.dagger2;

import android.os.Bundle;
import com.github.piasy.yamvp.YaPresenter;
import com.github.piasy.yamvp.YaView;
import com.github.piasy.yamvp.component.support.YaMvpFragment;
import javax.inject.Inject;

/**
 * Created by Piasy{github.com/Piasy} on 17/09/2016.
 */

public abstract class YaMvpDiFragment<V extends YaView, P extends YaPresenter<V>, C>
        extends YaMvpFragment<V, P> {

    @Inject
    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        C component = ((HasComponent<C>) getActivity()).getComponent();
        injectDependencies(component);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected final P createPresenter() {
        return mPresenter;
    }

    /**
     * inject dependencies.
     * Normally implementation should be {@code component.inject(this)}
     */
    protected abstract void injectDependencies(C component);
}
