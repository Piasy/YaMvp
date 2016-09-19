package com.github.piasy.yamvp.example;

import android.text.TextUtils;
import com.github.piasy.yamvp.rx.YaRxPresenter;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Piasy{github.com/Piasy} on 17/09/2016.
 */

class MainPresenter extends YaRxPresenter<MainView> {
    @Inject
    MainPresenter() {
    }

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
        listenPhoneNumberChanges();
    }

    private void listenPhoneNumberChanges() {
        addUtilStop(getView().phoneNumberChanges()
                .observeOn(Schedulers.computation())
                .map(phone -> !TextUtils.isEmpty(validateThenFormatMobileNumber(phone.toString())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(valid -> {
                    if (isViewAttached()) {
                        getView().showVerifyResult(valid);
                    }
                }, Throwable::printStackTrace));
    }

    private String validateThenFormatMobileNumber(String phone) {
        try {
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phone, "+86");
            if (phoneNumberUtil.isValidNumber(phoneNumber) && (
                    phoneNumberUtil.getNumberType(phoneNumber)
                            == PhoneNumberUtil.PhoneNumberType.MOBILE
                            || phoneNumberUtil.getNumberType(phoneNumber)
                            == PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE)) {
                return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
            } else {
                return "";
            }
        } catch (NumberParseException e) {
            return "";
        }
    }
}
