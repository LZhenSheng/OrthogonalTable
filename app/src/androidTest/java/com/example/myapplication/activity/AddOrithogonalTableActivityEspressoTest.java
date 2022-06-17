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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddOrithogonalTableActivityEspressoTest {
    @Rule
    public ActivityTestRule<AddOrithogonalTableActivity> mActivityTestRule = new ActivityTestRule<>(AddOrithogonalTableActivity.class);

    @Test
    public void addOrithogonalTableActivityEspressoTest() {
        try {
            //等待1秒钟
            //因为应用启动要时间
            sleep();

            //获取测试用例输入框和生成正交表按钮
            ViewInteraction appCompatButton = findViewById(R.id.generate);
            ViewInteraction appCompatEditText = findViewById(R.id.et_input);

            //第一个测试用例:空
            viewClick(appCompatButton);
            checkToast(R.string.err_not_null);
            sleep();

            //第二个测试用例:A:,A1,A2,A3 \n B:B1,B2,B3 \n C:C1,C2,C3
            editTextInput(appCompatEditText,"A:,A1,A2,A3\nB:B1,B2,B3\nC:C1,C2,C3");
            viewClick(appCompatButton);
            checkToast(R.string.err_format);
            sleep();

            //第三个测试用例:A：A1,A2,A3\nB:B1,B2,B3\nC:C1,C2,C3
            editTextInput(appCompatEditText,"A：A1,A2,A3\nB:B1,B2,B3\nC:C1,C2,C3");
            viewClick(appCompatButton);
            checkToast(R.string.err_not_character);
            sleep();

            //第四个测试用例:A:A1,A2,A3,A4,A5,A6\nB:B1,B2,B3,B4,B5,B6,B7\nC:C1,C2,C3,C4,C5\nD:D1,D2,D3,D4\nE:E1,E2,E3
            editTextInput(appCompatEditText,"A:A1,A2,A3,A4,A5,A6\nB:B1,B2,B3,B4,B5,B6,B7\nC:C1,C2,C3,C4,C5\nD:D1,D2,D3,D4\nE:E1,E2,E3");
            viewClick(appCompatButton);
            checkToast(R.string.err_not_support);
            sleep();

            //第五个测试用例:A:A1,A2,A3\nB:B1,B2,B3
            editTextInput(appCompatEditText,"A:A1,A2\nB:B1,B2");
            viewClick(appCompatButton);
            checkToast(R.string.err_not_support);
            sleep();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //延迟1秒钟
    private void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }

    //点击按钮
    private void viewClick(ViewInteraction view) {
        view.perform(click());
    }

    //EditText输入文本
    private void editTextInput(ViewInteraction view, String string) {
        view.perform(replaceText(string), closeSoftKeyboard());
    }

    //检查toast是否存在
    private void checkToast(int resourceId) {
        onView(withText(resourceId)).inRoot(RootMatchers.withDecorView(Matchers.not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    //根据Id查找控件
    private ViewInteraction findViewById(int id) {
        return onView(withId(id));
    }

    //根据Id查找控件
    private ViewInteraction findViewByContentDescription(String findViewByContentDescription) {

        return onView(withContentDescription(findViewByContentDescription));
    }

}
