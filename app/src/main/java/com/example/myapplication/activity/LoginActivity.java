package com.example.myapplication.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;
import com.example.myapplication.activity.base.BaseActivity;
import com.example.myapplication.util.PreferenceUtil;

import java.util.UUID;

/***
 * 首页
 * @author 胜利镇
 * @time 2021/3/15
 * @dec
 */
public class LoginActivity extends BaseActivity {

    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /***
     * 获取手机唯一的设备ID
     * @return
     */
    public static String getUniquePsuedoID() {

        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        String serial = null;
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            serial = "serial";
        }
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    @Override
    public void initData() {
        super.initData();
        fullScreen();
//        ToastUtil.successShortToast(getUniquePsuedoID());
        if(PreferenceUtil.getUserId()==null){
            PreferenceUtil.setUserId(getUniquePsuedoID());
        }
        timeOutFor1000();
    }

    /***
     * 演示1秒
     */
    protected void timeOutFor1000() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivityAfterFinishThis(MainActivity.class);
            }
        }, 1000);
    }

}
