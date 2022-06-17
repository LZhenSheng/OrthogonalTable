package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import com.example.myapplication.R;

import org.junit.Test;
import org.junit.runner.RunWith;


import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class LoginActviityTest6 {

    @Test
    public void testEspressoTestsApp() throws Exception {

//        //通过单例获取UiDevice
//        UiDevice uiDevice=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        //唤醒屏幕，屏幕已被唤醒则无效果
//        uiDevice.wakeUp();
//
//        //打开应用
//        UiObject app = uiDevice.findObject(new UiSelector().description("正交表测试工具"));
//        app.click();
//
//        //跳转到生成正交表界面
//        uiDevice.findObject(new UiSelector().resourceId("com.example.myapplication:id/action_settings")).click();
//
//        //获取生成正交表界面的控件
//        UiObject editText = uiDevice.findObject(new UiSelector().resourceId("com.example.myapplication:id/et_input"));
//        UiObject button = uiDevice.findObject(new UiSelector().resourceId("com.example.myapplication:id/agree"));
//
//        //第一个测试:空
//        button.click();
//        Thread.sleep(1000);
//
//        //第二个测试用例:A:A1，A2 \n B:B1，B2 \n C:C1，C2
//        editText.setText("A:A1，A2\nB:B1，B2\nC:C1，C2\n");
//        button.click();
//        Thread.sleep(1000);
//
//        //第三个测试用例:A:A1,A2,, \n B:B1,B2 \n C:C1,C2
//        editText.setText("A:A1,A2,,\nB:B1,B2\nC:C1,C2\n");
//        button.click();
//        Thread.sleep(1000);
//
//        //第四个测试用例:A:A1,A2
//        editText.setText("A:A1,A2");
//        button.click();
//        Thread.sleep(1000);
//
//        //第五个测试用例:A:A1,A2 \n B:B1,B2 \n C:C1,C2
//        editText.setText("A:A1,A2\nB:B1,B2\nC:C1,C2\n");
//        button.click();
//        Thread.sleep(1000);
//
//        //返回首页
//        uiDevice.pressBack();
//
//        //关闭应用
//        do{
//            app.waitForExists(2000);
//            if(app.exists()){
//                app.swipeLeft(5);
//            }
//        }while(app.exists());
    }
}
