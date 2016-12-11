package com.github.piasy.yamvp.example;

import dagger.Component;

/**
 * Created by Piasy{github.com/Piasy} on 18/09/2016.
 */

@Component
interface MainComponent {
    void inject(MainFragment fragment);
}
