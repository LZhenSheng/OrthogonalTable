package com.example.myapplication.adapter;

import android.app.Activity;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.bean.SearchResult;
import com.example.myapplication.bmob.History;
import com.example.myapplication.util.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 */
public class HistoryAdapter extends BaseQuickAdapter<History, BaseViewHolder> {

    /**
     * 构造方法
     *
     * @param layoutResId 布局Id
     */
    public HistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    /**
     * 显示数据
     *
     * @param helper
     * @param data
     */
    @Override
    protected void convert(@NonNull BaseViewHolder helper, History data) {
        Gson gson=new Gson();
        SearchResult searchResult=gson.fromJson(data.getData(),new TypeToken<SearchResult>(){}.getType());
        helper.setText(R.id.title,searchResult.getTitleT());
        helper.setText(R.id.content, TimeUtil.commonFormat(data.getDate()));
        helper.addOnClickListener(R.id.start);
    }

}