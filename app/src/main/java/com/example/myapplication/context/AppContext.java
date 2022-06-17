package com.example.myapplication.context;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.example.myapplication.util.PreferenceUtil;
import com.example.myapplication.util.ToastUtil;

import cn.bmob.v3.Bmob;
import es.dmoral.toasty.Toasty;

/***
* 全局配置文件
* @author 胜利镇
* @time 2020/8/6 22:45
*/
public class AppContext extends Application {


    /**
     * 偏好设置
     * 存储离线数据和特殊标记位
     */
    private PreferenceUtil sp;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Toast工具类
        Toasty.Config.getInstance().apply();

        MultiDex.install(this);

        //初始化toast工具类
        ToastUtil.init(getApplicationContext());

        //偏好设置初始化
        sp=PreferenceUtil.getInstance(getApplicationContext());

        //初始化Bmob
        Bmob.initialize(this,"0781caa7f2e03c4a606378c78a65b06a","demo");
    }

}