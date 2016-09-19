package com.github.piasy.yamvp.example;

import com.github.piasy.yamvp.dagger2.BaseComponent;
import dagger.Component;

/**
 * Created by Piasy{github.com/Piasy} on 18/09/2016.
 */

@Component
interface MainComponent extends BaseComponent<MainView, MainPresenter> {
    void inject(MainFragment fragment);
}
