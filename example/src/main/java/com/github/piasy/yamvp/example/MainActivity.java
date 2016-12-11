package com.github.piasy.yamvp.example;

import android.os.Bundle;
import android.util.Log;
import com.github.piasy.yamvp.dagger2.YaMvpDiActivity;

public class MainActivity extends YaMvpDiActivity<MainComponent> {

    private static final String MAIN_FRAGMENT = "MainFragment";
    private MainComponent mMainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("RetainInstance", "MainActivity#onCreate");
        if (savedInstanceState == null
            || getSupportFragmentManager().findFragmentByTag(MAIN_FRAGMENT) == null) {
            Log.d("RetainInstance", "MainActivity#addFragment");
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, new MainFragment(), MAIN_FRAGMENT)
                    .commit();
        }
    }

    @Override
    protected void initializeDi() {
        mMainComponent = DaggerMainComponent.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("RetainInstance", "MainActivity#onDestroy");
    }

    @Override
    public MainComponent getComponent() {
        return mMainComponent;
    }
}
