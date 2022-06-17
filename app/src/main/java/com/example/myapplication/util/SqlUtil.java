package com.example.myapplication.util;

import com.example.myapplication.bean.SearchResult;
import com.example.myapplication.bmob.History;
import com.google.gson.Gson;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SqlUtil {
    public static void saveHistory(String title[],String data[][]){
        SearchResult searchResult=new SearchResult();
        searchResult.setData(data);
        searchResult.setTitle(title);
        Gson gson=new Gson();
        History history=new History();
        history.setData(gson.toJson(searchResult));
        history.setDate(DateUtil.getDate());
        history.setId(PreferenceUtil.getUserId());
        history.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

            }
        });
    }
}
