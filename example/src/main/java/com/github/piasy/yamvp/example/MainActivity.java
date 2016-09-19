package com.github.piasy.yamvp.example;

import android.os.Bundle;
import com.github.piasy.yamvp.dagger2.YaMvpDiActivity;

public class MainActivity extends YaMvpDiActivity<MainView, MainPresenter, MainComponent> {

    private MainComponent mMainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, new MainFragment())
                .commit();
    }

    @Override
    protected void initializeDi() {
        mMainComponent = DaggerMainComponent.create();
    }

    @Override
    public MainComponent getComponent() {
        return mMainComponent;
    }
}
