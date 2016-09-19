package com.github.piasy.yamvp.example;

import com.github.piasy.yamvp.YaView;
import rx.Observable;

/**
 * Created by Piasy{github.com/Piasy} on 17/09/2016.
 */

interface MainView extends YaView {
    Observable<CharSequence> phoneNumberChanges();

    void showVerifyResult(boolean valid);
}
