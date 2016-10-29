package com.github.piasy.yamvp.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.piasy.yamvp.dagger2.YaMvpDiFragment;
import com.jakewharton.rxbinding.widget.RxTextView;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Flowable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends YaMvpDiFragment<MainView, MainPresenter, MainComponent>
        implements MainView {

    @BindView(R.id.mEtPhone)
    EditText mEtPhone;
    @BindView(R.id.mVerifyResult)
    TextView mVerifyResult;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d("RetainInstance", "MainFragment#onCreate");
    }

    @Override
    protected void injectDependencies(MainComponent component) {
        component.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("RetainInstance", "MainFragment#onDestroy");
    }

    @Override
    public Flowable<CharSequence> phoneNumberChanges() {
        return RxJavaInterop.toV2Flowable(RxTextView.textChanges(mEtPhone));
    }

    @Override
    public void showVerifyResult(boolean valid) {
        mVerifyResult.setText(valid ? R.string.valid_phone_number : R.string.invalid_phone_number);
    }
}
