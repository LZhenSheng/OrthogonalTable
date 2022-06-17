package com.example.myapplication.bmob;

import cn.bmob.v3.BmobObject;

/***
* 正交测试历史表
* @author 胜利镇
* @time 2021/3/16
* @dec
*/
public class History extends BmobObject {

    private String data;

    private String date;

    private String id;

    public String getData() {
        return data;
    }

    public void setData(String uri) {
        this.data = uri;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
