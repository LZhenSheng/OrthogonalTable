package com.example.myapplication.activity.base;

import android.os.Handler;

import com.example.myapplication.activity.MainActivity;

/***
* 页面跳转基类
* @author 胜利镇
* @time 2021/3/15
* @dec 
*/
public class BaseTimeActivity extends BaseActivity {
    /**
     * handler
     */
    Handler handler=new Handler();

    /***
     * 演示1秒
     */
    protected void timeOutFor1000() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 1000);
    }

    /***
     * 演示0.5秒
     */
    protected void timeOutFor500() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 500);
    }

    /***
     * 演示跳转主界面
     */
    protected void timeConsumingOperationLogin() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivityAfterFinishThis(MainActivity.class);
            }
        }, 1000);
    }

    /***
     * 演示关闭
     */
    protected void timeConsumingOperationFeedBack() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    @Override
    public void initData() {
        super.initData();
    }
}
