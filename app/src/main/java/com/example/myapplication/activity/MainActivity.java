package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.base.BaseTitleActivity;
import com.example.myapplication.adapter.HistoryAdapter;
import com.example.myapplication.bean.SearchResult;
import com.example.myapplication.bmob.History;
import com.example.myapplication.util.ClickUtil;
import com.example.myapplication.util.DateUtil;
import com.example.myapplication.util.PreferenceUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/***
* 主页面
* @author 胜利镇
* @time 2021/3/15
* @dec 
*/
public class MainActivity extends BaseTitleActivity {

    @BindView(R.id.activity_main)
    PullToRefreshLayout pullToRefreshLayout;

    HistoryAdapter adapter;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 显示返回按钮
     */
    protected void showBackMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /**
     * 创建菜单方法
     *
     * 有点类似显示布局要写到onCreate方法一样
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 菜单点击了回调
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(ClickUtil.isFastClick()){
            return super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(AddOrithogonalTableActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViews() {
        super.initViews();
        //尺寸固定
        rv.setHasFixedSize(true);

        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMainActivity());
        rv.setLayoutManager(layoutManager);
        rv.setNestedScrollingEnabled(false);
        //创建分割线
        DividerItemDecoration decoration=new DividerItemDecoration(getMainActivity(),RecyclerView.VERTICAL);
        //添加到控件
        //可以添加多个
        rv.addItemDecoration(decoration);

        //禁用嵌套滚动
        rv.setNestedScrollingEnabled(false);

        //创建适配器
        adapter = new HistoryAdapter(R.layout.item_history);

        //设置适配器
        rv.setAdapter(adapter);

        //设置子元素点击事件
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Intent intent=new Intent(MainActivity.this,HistoryDisPlayActivity.class);
            Gson gson=new Gson();
            SearchResult searchResult=gson.fromJson(get(position),new TypeToken<SearchResult>(){}.getType());
            Bundle bundle = new Bundle();
            bundle.putSerializable("header", searchResult.getTitle());
            bundle.putSerializable("content", searchResult.getData());
            intent.putExtras(bundle);
            startActivity(intent);
        });

        next();
    }


    @Override
    protected void initListener() {
        super.initListener();
        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next();
                        // 结束刷新
                        pullToRefreshLayout.finishRefresh();
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // 结束加载更多
                        pullToRefreshLayout.finishLoadMore();
                    }
                }, 2000);
            }
        });

    }

    /**
     * 显示数据
     */
    private void next() {
        BmobQuery<History> bmobQuery=new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<History>() {
            @Override
            public void done(List<History> list, BmobException e) {
                if(e==null){
                    List<History> list1=new ArrayList<>();
                    Log.d(TAG, "done: "+list.size());
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).getId().equals(PreferenceUtil.getUserId())){
                            list1.add(list.get(i));
                        }
                    }
                    Collections.reverse(list1);
                    adapter.replaceData(list1);
                }
            }
        });

    }

    /***
     * 获取ObjectID跳转
     * @param position
     */
    public String get(int position){
        return adapter.getData().get(position).getData();
//        PreferenceUtil.saveFundraising(adapter.getData().get(position).getObjectId());
    }
}
