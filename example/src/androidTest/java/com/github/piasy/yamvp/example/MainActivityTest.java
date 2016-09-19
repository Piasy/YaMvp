package com.github.piasy.yamvp.example;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.azimolabs.conditionwatcher.ConditionWatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws Exception {
        ConditionWatcher.waitForCondition(new FragmentResumedInstruction());

        ViewInteraction textView = onView(
                allOf(withId(R.id.mVerifyResult), withText("invalid phone number"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("invalid phone number")));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.mEtPhone), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.mEtPhone), isDisplayed()));
        appCompatEditText2.perform(replaceText("+86"), closeSoftKeyboard());

        Thread.sleep(BuildConfig.TEST_WAIT_BEFORE_VERIFY);

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.mVerifyResult), withText("invalid phone number"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("invalid phone number")));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.mEtPhone), isDisplayed()));
        appCompatEditText3.perform(replaceText("+8613312345678"), closeSoftKeyboard());

        Thread.sleep(BuildConfig.TEST_WAIT_BEFORE_VERIFY);

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.mVerifyResult), withText("valid phone number"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("valid phone number")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
