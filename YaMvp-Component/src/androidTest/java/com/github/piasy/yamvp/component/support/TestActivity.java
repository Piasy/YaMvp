package com.github.piasy.yamvp.component.support;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    private TestFragment mTestFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void startTestFragment(TestPresenter testPresenter) {
        mTestFragment = new TestFragment();
        mTestFragment.setTestPresenter(testPresenter);
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, mTestFragment)
                .commit();
    }

    TestFragment getTestFragment() {
        return mTestFragment;
    }
}
