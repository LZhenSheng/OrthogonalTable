package com.example.myapplication.activity;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.myapplication.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest3 {

    @Rule
    public ActivityTestRule<AddOrithogonalTableActivity> mActivityTestRule = new ActivityTestRule<>(AddOrithogonalTableActivity.class);

    @Test
    public void loginActivityTest3() {

//        try {
//            //等待1秒钟
//            //因为应用启动要时间
//            Thread.sleep(1000);
//
//            //获取测试用例输入框和生成正交表按钮
//            ViewInteraction appCompatButton = onView(allOf(withId(R.id.agree), withText("生成正交表"),childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 1)));
//            ViewInteraction appCompatEditText = onView(allOf(withId(R.id.et_input), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 0)));
//
//            //第一个测试用例:空
//            appCompatButton.perform(scrollTo(), click());
//            onView(withText(R.string.err_not_null)).inRoot(RootMatchers.withDecorView(Matchers.not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
//            Thread.sleep(1000);
//
//            //第二个测试用例:A:A1，A2 \n B:B1，B2 \n C:C1，C2
//            appCompatEditText.perform(scrollTo(), replaceText("A:A1，A2\nB:B1，B2\nC:C1，C2\n"), closeSoftKeyboard());
//            appCompatButton.perform(scrollTo(), click());
//            onView(withText(R.string.err_not_character)).inRoot(RootMatchers.withDecorView(Matchers.not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
//            Thread.sleep(1000);
//
//            //第三个测试用例:A:A1,A2,, \n B:B1,B2 \n C:C1,C2
//            appCompatEditText.perform(scrollTo(), replaceText("A:A1,A2,,\nB:B1,B2\nC:C1,C2\n"), closeSoftKeyboard());
//            appCompatButton.perform(scrollTo(), click());
//            onView(withText(R.string.err_format)).inRoot(RootMatchers.withDecorView(Matchers.not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
//            Thread.sleep(1000);
//
//            //第四个测试用例:A:A1,A2
//            appCompatEditText.perform(scrollTo(), replaceText("A:A1,A2"), closeSoftKeyboard());
//            appCompatButton.perform(scrollTo(), click());
//            onView(withText(R.string.err_not_support)).inRoot(RootMatchers.withDecorView(Matchers.not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
//            Thread.sleep(1000);
//
//            //第五个测试用例:A:A1,A2 \n B:B1,B2 \n C:C1,C2
//            appCompatEditText.perform(scrollTo(), replaceText("A:A1,A2\nB:B1,B2\nC:C1,C2\n"), closeSoftKeyboard());
//            appCompatButton.perform(scrollTo(), click());
//            onView(withText(R.string.loading)).inRoot(RootMatchers.withDecorView(Matchers.not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
//            Thread.sleep(1000);
//
//            //返回到首页
//            ViewInteraction appCompatImageButton = onView(allOf(withContentDescription("转到上一层级"), childAtPosition(allOf(withId(R.id.toolbar), childAtPosition(withClassName(is("com.google.android.material.appbar.AppBarLayout")), 0)), 1), isDisplayed()));
//            appCompatImageButton.perform(click());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
