package com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.base.BaseTitleActivity;
import com.example.myapplication.view.TableView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HistoryDisPlayActivity extends BaseTitleActivity {

    private List<String[]> data1;
    private String[] title;

    @BindView(R.id.num)
    TextView num;

    @BindView(R.id.table)
    TableView tableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_dis_play);
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle=getIntent().getExtras();
        //数据表头
        title=(String[])bundle.getSerializable("header");
        String [][]data=(String[][])bundle.getSerializable("content");

        //数据内容
        data1=new ArrayList<>();
        int k=0;
        for(int i=0;i<data.length;i++){
            data1.add(data[i]);
        }
        num.setText("n="+title.length);
        int row=data.length;
        tableView.clearTableContents()
                .setHeader(title)
                .addContents(data1)
                .refreshTable();
    }
}
