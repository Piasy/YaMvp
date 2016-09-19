package com.github.piasy.yamvp.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.piasy.yamvp.dagger2.YaMvpDiFragment;
import com.jakewharton.rxbinding.widget.RxTextView;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends YaMvpDiFragment<MainView, MainPresenter, MainComponent>
        implements MainView {

    public MainFragment() {
        // Required empty public constructor
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
    protected void injectDependencies(MainComponent component) {
        component.inject(this);
    }

    @BindView(R.id.mEtPhone)
    EditText mEtPhone;
    @BindView(R.id.mVerifyResult)
    TextView mVerifyResult;

    @Override
    public Observable<CharSequence> phoneNumberChanges() {
        return RxTextView.textChanges(mEtPhone);
    }

    @Override
    public void showVerifyResult(boolean valid) {
        mVerifyResult.setText(valid ? R.string.valid_phone_number : R.string.invalid_phone_number);
    }
}
